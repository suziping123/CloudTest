package org.cloud.order.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
    public Product getProduct(@PathVariable("id") Long id,
    HttpServletRequest request) throws InterruptedException {
        String header = request.getHeader("X-Token");
        System.out.println("hello……"+ header);
        Thread.sleep(50); // 模拟慢响应，触发超时重试
        Product product = productService.getProductById(id);
        return product;
    }
}
