package com.gulkalkan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.gulkalkan"})
@EntityScan(basePackages = {"com.gulkalkan"})
@EnableJpaRepositories(basePackages = {"com.gulkalkan"})
@SpringBootApplication
public class JwtApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplicationStarter.class, args);
    }

}
