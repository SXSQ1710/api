package com.sxsq.sxgateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @title: CustomGlobalFilter
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/8 23:26
 **/

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered  {

    private static final List<String> IP_WHITE_LIST = Collections.singletonList("127.0.0.1");

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

        // 1. 请求日志
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + request.getPath().value());
        log.info("请求方法：" + request.getMethod());
        log.info("请求参数：" + request.getQueryParams());
        String hostString = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        log.info("请求来源地址：" + hostString);
        // 2. 访问控制 -黑白名单
        if(IP_WHITE_LIST.contains(hostString)){
            response.setStatusCode(HttpStatus.FORBIDDEN);
            // setComplete(): 直接生成一个Mono
            return response.setComplete();
        }

        log.info("custom global filter");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
