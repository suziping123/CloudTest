package org.cloud.order.service.iml;

import lombok.extern.slf4j.Slf4j;
import org.cloud.model.bean.order.Order;
import org.cloud.model.bean.product.Product;
import org.cloud.order.config.ProductFeignClient;
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
    private ProductFeignClient productFeignClient;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    public Product getProductById(Long id) {
        log.info("通过 Feign 调用商品服务，productId={}", id);
        return productFeignClient.getProductById(id);
    }

    @Override
    public Order getOrderById(Long orderId, Long productId) {
        Product product = getProductById(productId);
        Order order = OrderService.createOrder(orderId, product);
        return order;
    }
}
