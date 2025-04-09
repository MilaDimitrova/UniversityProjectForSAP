package com.example.garbandgo.service;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductRepository() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
