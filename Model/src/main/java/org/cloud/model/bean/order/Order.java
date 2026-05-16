package org.cloud.model.bean.order;

import lombok.Data;
import org.cloud.model.bean.product.Product;

import java.util.List;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Data
public class Order {
    private Long id;
    private Long totalAmount;
    private Long userId;
    private String nickName;
    private List<Product> productList;
}
