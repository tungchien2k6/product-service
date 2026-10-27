package com.ra.productservice.service.impl;

import com.ra.productservice.entity.Product;
import com.ra.productservice.repository.ProductRepository;
import com.ra.productservice.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        System.out.println("=== Truy vấn Database lấy thông tin sản phẩm ID: " + id + " ===");
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        System.out.println("=== Cập nhật sản phẩm ID " + product.getId() + " trong Database và xóa Cache ===");

        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + product.getId()));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }
}
