package com.example.garbandgo.service;

import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }


    public Optional<ProductCategory> findById(Integer id) {
        return productCategoryRepository.findById(id);
    }
}