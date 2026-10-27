package com.ra.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<String> products = new ArrayList<>(Arrays.asList("Laptop", "Smartphone", "Tablet"));

    @GetMapping
    public ResponseEntity<List<String>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (id >= 0 && id < products.size()) {
            String removed = products.remove(id);
            return ResponseEntity.ok("Đã xóa sản phẩm: " + removed);
        }
        return ResponseEntity.ok("Đã xóa sản phẩm thành công!");
    }
}