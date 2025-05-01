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


    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getAllReviews() {
        return getAll();
    }

    public Review getById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    public Review getReviewById(Integer id) {
        return getById(id);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void saveReview(Review review) {
        save(review);
    }

    public Review update(Integer id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            updatedReview.setId(id);
            return reviewRepository.save(updatedReview);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteReview(Integer id) {
        delete(id);
    }
}
