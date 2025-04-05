package repositories;

import entities.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Integer> {
}