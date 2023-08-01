package com.sxsq.sxinterface;

import com.sxsq.sxclientsdk.client.SxApiClient;
import com.sxsq.sxclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SxInterfaceApplicationTests {

    @Resource
    private SxApiClient sxApiClient;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("SXSQ");
        String usernameByPost = sxApiClient.getUsernameByPost(user);
        System.out.println(usernameByPost);
    }

}
