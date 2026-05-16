package org.cloud.order.service.iml;

import lombok.extern.slf4j.Slf4j;
import org.cloud.model.bean.order.Order;
import org.cloud.model.bean.product.Product;
import org.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SuZiPing
 * @version 1.0
 */

@Slf4j
@Service
public class OrderServiceIml implements OrderService {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    public Product getProductById(Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + id;
        System.out.println(url);

        log.info("url="+url);
        return restTemplate.getForObject(url, Product.class);
    }

    @Override
    public Order getOrderById(Long orderId, Long productId) {
        Product product = getProductById(productId);
        Order order = OrderService.createOrder(orderId, product);
        return null;
    }
}
