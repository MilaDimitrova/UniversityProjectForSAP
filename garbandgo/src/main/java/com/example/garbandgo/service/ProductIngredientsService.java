package com.example.garbandgo.service;

import com.example.garbandgo.entities.ProductIngredient;
import com.example.garbandgo.repositories.ProductIngredientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductIngredientsService {

    private final ProductIngredientRepository productIngredientRepository;

    public ProductIngredientsService(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }

    public List<ProductIngredient> getProductIngrediantsRepository() {
        return productIngredientRepository.findAll();
    }

    public void saveProductIngredient(ProductIngredient productIngredient) {
        productIngredientRepository.save(productIngredient);
    }
}
