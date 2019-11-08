package com.pcsilva.microservice;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@EnableTurbine
@Configuration
@EnableDiscoveryClient
public class TurbineApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TurbineApplication.class).run(args);
    }

}
