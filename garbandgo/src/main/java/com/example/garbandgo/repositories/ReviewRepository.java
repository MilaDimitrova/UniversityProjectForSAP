package com.example.garbandgo.repositories;

import com.example.garbandgo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Метод за намиране на ревюта по orderId
    List<Review> findByOrderId(Integer orderId);
}
