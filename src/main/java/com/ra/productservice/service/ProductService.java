package com.ra.productservice.service;

import com.ra.productservice.entity.Product;

public interface ProductService {
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
}
