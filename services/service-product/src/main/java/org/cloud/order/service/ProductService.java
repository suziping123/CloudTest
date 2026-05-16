package org.cloud.order.service;


import org.cloud.model.bean.product.Product;

/**
 * @author SuZiPing
 * @version 1.0
 */


public interface ProductService {


    Product getProductById(Long id);
}
