package com.diy.dagon.config;

import com.diy.dagon.remote.RpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dagon on 2019/6/29 - 11:30
 */
@Configuration
@ComponentScan(basePackages = "com.diy.dagon")
public class SpringConfig {

    @Bean
    public RpcServer rpcServer(){
        return new RpcServer(7777);
    }
}
