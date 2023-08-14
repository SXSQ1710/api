package com.SXSQ.sxclientsdk;

import com.SXSQ.sxclientsdk.client.SxApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @title: SxApiClientConfig
 * @Author SXSQ
 * @Description //TODO
 * @Date 2023/8/1 23:09
 **/

@Configuration
@ConfigurationProperties("sx-api.client")
@ComponentScan
@Data
public class SxApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public SxApiClient sxApiClient() {
        SxApiClient sxApiClient = new SxApiClient();
        sxApiClient.setAccessKey(accessKey);
        sxApiClient.setSecretKey(secretKey);
        return sxApiClient;
    }
}
