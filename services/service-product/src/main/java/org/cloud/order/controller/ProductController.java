package org.cloud.order.controller;

import org.cloud.model.bean.product.Product;
import org.cloud.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author SuZiPing
 * @version 1.0
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {

        Product product = productService.getProductById(id);
        return product;
    }
}
