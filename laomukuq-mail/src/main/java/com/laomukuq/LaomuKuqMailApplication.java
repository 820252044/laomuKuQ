package com.laomukuq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LaomuKuqMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaomuKuqMailApplication.class, args);
    }
}
