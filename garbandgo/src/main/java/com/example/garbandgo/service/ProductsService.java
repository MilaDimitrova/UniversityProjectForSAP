package com.example.garbandgo.service;

import com.example.garbandgo.entities.Product;
import com.example.garbandgo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProductsByRestaurantId(Integer restaurantId) {
        return productRepository.findByRestaurantId(restaurantId);
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
