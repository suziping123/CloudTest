package org.cloud.order;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @author SuZiPing
 * @version 1.0
 */

@SpringBootTest
public class SpringDiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    void discoveryClientTest() {
        for (String serviceName : discoveryClient.getServices()) {
            System.out.println("serviceName="+serviceName);
            // 获取ip和端口
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                System.out.println("instance.getHost()="+instance.getHost());
                System.out.println("instance.getPort()="+instance.getPort());
            }
        }
    }

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws Exception {
        for (String serviceName : nacosServiceDiscovery.getServices()) {
            System.out.println("serviceName="+serviceName);
            // 获取ip和端口
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                System.out.println("instance.getHost()="+instance.getHost());
                System.out.println("instance.getPort()="+instance.getPort());
            }
        }
    }
}
