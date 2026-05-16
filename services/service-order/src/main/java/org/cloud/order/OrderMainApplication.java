package org.cloud.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author SuZiPing
 * @version 1.0
 */
@SpringBootApplication
class OrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }


    // 1、启动项目监听配置变化事件
    // 2、监听到事件后，获取配置信息
    // 3、发布一个自定义事件
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        return args -> {
            System.out.println("=".repeat(60));
            System.out.println("OrderMainApplication started successfully!");
            nacosConfigManager.getConfigService().addListener("order-service.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(4);
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("变化的配置信息："+configInfo);
                    System.out.println("邮件通知……");

                }
            });
            // 发布一个自定义事件
            ApplicationEvent event = new ApplicationEvent("OrderMainApplication is up and running!") {};
//            SpringApplication.run(OrderMainApplication.class).publishEvent(event);
            System.out.println("=".repeat(60));
        };
    }
}