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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProductsByRestaurantId(Integer restaurantId) {
        return productRepository.findByRestaurantId(restaurantId);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id " + id));
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
