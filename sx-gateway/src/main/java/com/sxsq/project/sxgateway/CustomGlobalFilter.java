package com.SXSQ.project.sxgateway;

import com.SXSQ.common.model.entity.InterfaceInfo;
import com.SXSQ.common.model.entity.User;
import com.SXSQ.common.service.InnerInterfaceInfoService;
import com.SXSQ.common.service.InnerUserInterfaceInfoService;
import com.SXSQ.common.service.InnerUserService;
import com.SXSQ.sxclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @title: CustomGlobalFilter
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/8 23:26
 **/

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;
    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @DubboReference
    private InnerUserService innerUserService;

    private static final List<String> IP_WHITE_LIST = Collections.singletonList("127.0.1.1");

    /**
     * 全局过滤
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getPath().value();
        String method = Objects.requireNonNull(request.getMethod()).toString();
        // 1. 请求日志
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + path);
        log.info("请求方法：" + method);
        log.info("请求参数：" + request.getQueryParams());
        String hostString = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        log.info("请求来源地址：" + hostString);
        // 2. 访问控制 -黑白名单
        if (IP_WHITE_LIST.contains(hostString)) {
            return handNoAuth(response);
        }

        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");

        // 校验谁技术
        if (nonce == null || Long.parseLong(nonce) > 10000L) {
            return handNoAuth(response);
        }
        // 验证请求时间和当前时间不超过 5 分钟
        long FIVE_MINUTES = 60 * 5L;
        long now = System.currentTimeMillis() / 1000;
        if (timestamp == null || (now - Long.parseLong(timestamp)) >= FIVE_MINUTES || (now - Long.parseLong(timestamp)) < 0L) {
            return handNoAuth(response);
        }

        // todo 从数据库中查询用户的secretKey（缓存升级）
        User user = null;
        try {
            user = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error: ", e);
        }
        // 验证签名是否合法
        if (sign == null || user == null || !sign.equals(SignUtils.getSign(body, user.getSecretKey()))) {
            return handNoAuth(response);
        }

        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInvokeUser error: ", e);
        }
        // todo 检验请求接口是否存在（缓存升级）
        if (interfaceInfo == null) {
            return handNoAuth(response);
        }

        return responseLogGlobalFilter(exchange, chain);

//        return chain.filter(exchange);
    }

    public Mono<Void> responseLogGlobalFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                // 进行装饰
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        //log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里写数据
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                //rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                log.info(sb2.toString(), rspArgs.toArray());//log.info("<-- {} {}\n", originalResponse.getStatusCode(), data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        // setComplete(): 直接生成一个Mono
        return response.setComplete();
    }

    public Mono<Void> handInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        // setComplete(): 直接生成一个Mono
        return response.setComplete();
    }

}
