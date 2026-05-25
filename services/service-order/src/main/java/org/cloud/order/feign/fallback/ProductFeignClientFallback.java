package org.cloud.order.feign.fallback;

import org.cloud.model.bean.product.Product;
import org.cloud.order.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调……");
        Product product = new Product();
        product.setId(id);
        product.setName("商品不存在");
        product.setPrice(new BigDecimal(0));
        product.setNum(0);
        product.setProductName("未知商品");

        return product;
    }
}
