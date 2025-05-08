package com.example.garbandgo.service;

import com.example.garbandgo.entities.Review;
import com.example.garbandgo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Получаване на всички ревюта
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Получаване на ревю по ID
    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Съхраняване или актуализиране на ревю
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    // Изтриване на ревю по ID
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    // Получаване на ревюта по конкретен orderId
    public List<Review> getReviewsByOrderId(Integer orderId) {
        return reviewRepository.findByOrderId(orderId);
    }
}
