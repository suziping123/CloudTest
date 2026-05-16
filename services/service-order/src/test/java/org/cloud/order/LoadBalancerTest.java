package org.cloud.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 * @author SuZiPing
 * @version 1.0
 */
@SpringBootTest
public class LoadBalancerTest {
    @Autowired
    private LoadBalancerClient loadBalancer;
    DiscoveryClient discoveryClient;

    @Test
    void loadBalancerTest() {
        ServiceInstance choose = loadBalancer.choose("SpringBoot-Order");
        System.out.println("choose=" + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancer.choose("SpringBoot-Order");
        System.out.println("choose=" + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancer.choose("SpringBoot-Order");
        System.out.println("choose=" + choose.getHost() + ":" + choose.getPort());
        choose = loadBalancer.choose("SpringBoot-Order");
        System.out.println("choose=" + choose.getHost() + ":" + choose.getPort());
    }
}
