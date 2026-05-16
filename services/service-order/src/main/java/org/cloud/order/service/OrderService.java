package org.cloud.order.service;

import org.cloud.model.bean.order.Order;
import org.cloud.model.bean.product.Product;

/**
 * @author SuZiPing
 * @version 1.0
 */
public interface OrderService {
    static Order createOrder(Long orderId, Product product) {
        Order order = new Order();
        order.setId(orderId);
        order.setTotalAmount(product.getPrice().longValue());
        order.setProductList(java.util.Arrays.asList(product));
        order.setNickName("SuZiPing");
        order.setUserId(1L);

        return order;
    };

    public Order getOrderById(Long orderId, Long productId);
}
