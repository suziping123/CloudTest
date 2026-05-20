package org.cloud.order.config;

import feign.Logger;
import feign.RetryableException;
import feign.Retryer;
import org.cloud.order.interceptor.XTokenRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Configuration
public class OrderConfig {

    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }
    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public XTokenRequestInterceptor xTokenRequestInterceptor() {
        return new XTokenRequestInterceptor();
    }
}
