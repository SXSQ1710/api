package com.SXSQ.sxinterface.controller;

import com.SXSQ.sxclientsdk.model.User;
import com.sxsq.sxinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: TestController
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/1 22:17
 **/
@RestController
@RequestMapping("/name")
public class TestController {
    private final String secretKey = "dd08d221886d4e4bae8250d354c5c2f1";

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");

//        if (!sign.equals(SignUtils.getSign(body,secretKey))){
//            System.out.println("error");
//        }
        return "用户是："+ user.getUsername();
    }

    @GetMapping("/user/{name}")
    public String getUserNameByGet(@PathVariable("name") String name, HttpServletRequest request){
        return "用户是："+ name;
    }
}
