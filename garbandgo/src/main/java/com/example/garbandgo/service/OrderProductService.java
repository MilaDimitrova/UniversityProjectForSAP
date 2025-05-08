package com.example.garbandgo.service;

import com.example.garbandgo.entities.OrderProduct;
import com.example.garbandgo.repositories.OrderProductRepository;
import com.example.garbandgo.entities.Product;
import com.example.garbandgo.repositories.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    // Retrieve all order products
    public List<OrderProduct> getAll() {
        return orderProductRepository.findAll();
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a specific order product by its ID
    public OrderProduct getById(Integer id) {
        Optional<OrderProduct> orderProduct = orderProductRepository.findById(id);
        return orderProduct.orElse(null);
    }

    // Save a new order product
    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    // Update an existing order product
    public OrderProduct update(Integer id, OrderProduct updated) {
        Optional<OrderProduct> existingOrderProduct = orderProductRepository.findById(id);
        if (existingOrderProduct.isPresent()) {
            updated.setId(id);
            return orderProductRepository.save(updated);
        }
        return null;
    }

    // Delete an order product by its ID
    public boolean delete(Integer id) {
        if (orderProductRepository.existsById(id)) {
            orderProductRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
