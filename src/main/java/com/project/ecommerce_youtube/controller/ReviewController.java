package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Review;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.ReviewRequest;
import com.project.ecommerce_youtube.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request, @RequestParam User user) {
        try {
            Review review = reviewService.createReview(request, user);
            return ResponseEntity.ok(review);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getAllReview(productId);
        return ResponseEntity.ok(reviews);
    }


}
