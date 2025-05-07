package com.example.garbandgo.service;

import com.example.garbandgo.entities.Review;
import com.example.garbandgo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Връща всички рецензии от базата.
     */
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    /**
     * Съвместим метод за контролера.
     */
    public List<Review> getAllReviews() {
        return getAll();
    }

    /**
     * Намира рецензия по ID.
     */
    public Review getById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    /**
     * Съвместим метод за контролера.
     */
    public Review getReviewById(Integer id) {
        return getById(id);
    }

    /**
     * Запазва нова рецензия.
     */
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Съвместим метод за контролера.
     */
    public void saveReview(Review review) {
        save(review);
    }

    /**
     * Актуализира рецензия по ID.
     */
    public Review update(Integer id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            updatedReview.setId(id);
            return reviewRepository.save(updatedReview);
        }
        return null;
    }

    /**
     * Изтрива рецензия по ID.
     */
    public boolean delete(Integer id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Съвместим метод за контролера.
     */
    public void deleteReview(Integer id) {
        delete(id);
    }
}
