package com.SXSQ.project.sxgateway;


import com.SXSQ.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubbo
public class SxGatewayApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SxGatewayApplication.class, args);
        SxGatewayApplication application = context.getBean(SxGatewayApplication.class);
        String s = application.doSayHello("SXSQ");
        System.out.println(s);
    }

    @DubboReference
    private DemoService demoService;

    public String doSayHello (String name) {return demoService.sayHello(name);}
}
