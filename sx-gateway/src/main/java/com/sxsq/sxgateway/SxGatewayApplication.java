package com.sxsq.sxgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SxGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SxGatewayApplication.class, args);
    }
}
