package com.uter.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.uter",
        "com.uter.backoffice", "com.uter.backoffice.repository"})
public class BackofficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);
    }

}
