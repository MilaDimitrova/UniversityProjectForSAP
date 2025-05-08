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

    public List<OrderProduct> getAll() {
        return orderProductRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public OrderProduct getById(Integer id) {
        return orderProductRepository.findById(id).orElse(null);
    }

    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    public OrderProduct update(Integer id, OrderProduct updated) {
        Optional<OrderProduct> existingOrderProduct = orderProductRepository.findById(id);
        if (existingOrderProduct.isPresent()) {
            updated.setId(id);
            return orderProductRepository.save(updated);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (orderProductRepository.existsById(id)) {
            orderProductRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<OrderProduct> getByOrderId(Integer orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }
}