package org.cloud.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Configuration
public class ProductConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
