package com.example.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 代志豪
 * 2022/3/24 20:21
 */
@SpringBootApplication
@EnableEurekaClient
public class Test1Application {
    public static void main(String[] args) {
        SpringApplication.run(Test1Application.class, args);
    }
}
