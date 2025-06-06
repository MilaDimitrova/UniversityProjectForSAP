package com.example.garbandgo.controller;

import com.example.garbandgo.entities.Review;
import com.example.garbandgo.entities.Order;
import com.example.garbandgo.service.ReviewService;
import com.example.garbandgo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", new Review());
        return "Orders/review_list";
    }

    @GetMapping("/create")
    public String showCreateReviewForm(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("review", new Review());
        return "Orders/review_form";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute Review review) {
        review.setDateCreated(Instant.now());
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditReviewForm(@PathVariable Integer id, Model model) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            List<Order> orders = orderService.getAllOrders();
            model.addAttribute("orders", orders);
            model.addAttribute("review", review);
            return "Orders/review_form";
        } else {
            return "redirect:/reviews";
        }
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable Integer id, @ModelAttribute Review review) {
        review.setId(id);
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}
