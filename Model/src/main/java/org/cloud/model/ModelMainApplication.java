package org.cloud.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author SuZiPing
 * @version 1.0
 */

@EnableDiscoveryClient // 开启服务注册与发现功能
@SpringBootApplication
public class ModelMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModelMainApplication.class, args);
    }
}