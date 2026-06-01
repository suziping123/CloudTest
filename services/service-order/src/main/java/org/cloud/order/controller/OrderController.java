package org.cloud.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.cloud.model.bean.order.Order;
import org.cloud.model.bean.product.Product;
import org.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


/**
 * @author SuZiPing
 * @version 1.0
 */
@Slf4j
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

    public Order createOrder(Long orderId, Long productId) {
        Order order = new Order();
        order.setId(orderId);
        order.setProductId(productId);
        order.setStatus("fallback");
        return order;
    }

    @GetMapping("seckill")
    public Order seckill(Long orderId, Long productId) {
        return OrderService.createOrder(Long.MAX_VALUE, new Product(Long.MAX_VALUE, new BigDecimal(1),"秒杀商品",1));
    }

    @GetMapping("/readDB")
    public String readDB() {
        log.info("正在读取……");
        return "读取数据库成功！";
    }

    @GetMapping("/writeDB")
    public String writeDB() {
        log.info("正在写入……");
        return "写入数据库成功！";
    }
}
