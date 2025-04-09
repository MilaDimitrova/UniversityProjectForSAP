package com.example.garbandgo.controller;

import com.example.garbandgo.entities.ProductCategory;
import com.example.garbandgo.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;


    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getProductCategoryRepository() {
        return productCategoryRepository.findAll();
    }

    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }
}
