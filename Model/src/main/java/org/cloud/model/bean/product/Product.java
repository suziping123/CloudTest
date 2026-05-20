package org.cloud.model.bean.product;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private BigDecimal price;
    private String productName;
    private int num;

    public void setName(String s) {
    }
}
