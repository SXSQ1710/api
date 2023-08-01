package com.sxsq.sxclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.sxsq.sxclientsdk.model.User;
import com.sxsq.sxclientsdk.utils.SignUtils;
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

    public Map<String, String> getHeadMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.getSign(body, secretKey));
        return hashMap;
    }


    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeadMap(json))
                .body(json)
                .execute();
        System.out.println(execute.getStatus());
        System.out.println(execute.body());
        return execute.body();
    }

}
