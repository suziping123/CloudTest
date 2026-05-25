package org.cloud.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.cloud.model.bean.order.Order;
import org.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author SuZiPing
 * @version 1.0
 */

@RestController
@RefreshScope
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${order.timeout:默认值}")
    private String timeout;

    @GetMapping("/timeout")
    public String getTimeout() {
        return "当前 order.timeout = " + timeout;
    }
    @SentinelResource(value = "getOrderController")
    @GetMapping("/order/{orderId}&{productId}")
    public Order  getOrderController(@PathVariable("orderId") Long orderId,
                                     @PathVariable("productId") Long productId) {

        Order order = orderService.getOrderById(orderId, productId);
        return order;
    }
}
