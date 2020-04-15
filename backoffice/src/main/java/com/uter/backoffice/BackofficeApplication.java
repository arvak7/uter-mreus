package com.uter.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BackofficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);
    }

}
