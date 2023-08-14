package com.SXSQ.sxclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.SXSQ.sxclientsdk.model.User;
import com.SXSQ.sxclientsdk.utils.SignUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: SxApiClient
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/1 21:58
 **/

@Data
public class SxApiClient {

    private String accessKey;

    private String secretKey;

    public SxApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public SxApiClient() {

    }


    public Map<String, String> getHeadMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 创建随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        // 加入时间戳
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 通过secretKey加密body
        hashMap.put("sign", SignUtils.getSign(body, secretKey));
        return hashMap;
    }


    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://localhost:8090/api/name/user")
                .addHeaders(getHeadMap(json))
                .body(json)
                .execute();
        System.out.println(execute.getStatus());
        System.out.println(execute.body());
        return execute.body();
    }

}
