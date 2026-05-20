package org.cloud.order.service.iml;

import lombok.extern.slf4j.Slf4j;
import org.cloud.model.bean.product.Product;
import org.cloud.order.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ProductServiceIml implements ProductService {

    private static final Map<Long, Product> PRODUCT_DB = new HashMap<>();

    static {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("iPhone 15");
        product1.setPrice(new BigDecimal("5999.00"));
        PRODUCT_DB.put(1L, product1);

        Product product6 = new Product();
        product6.setId(6L);
        product6.setName("MacBook Pro");
        product6.setPrice(new BigDecimal("12999.00"));
        PRODUCT_DB.put(6L, product6);
    }

    public Product getProductById(Long id) {
        log.info("查询商品，id={}", id);
        Product product = PRODUCT_DB.get(id);
        if (product == null) {
            log.error("商品不存在，id={}", id);
            throw new RuntimeException("商品不存在: " + id);
        }
        return product;
    }
}
