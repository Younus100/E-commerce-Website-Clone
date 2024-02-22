package com.project.ecommerce_youtube.controller;


import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.Rating;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.RatingRequest;
import com.project.ecommerce_youtube.service.RatingService;
import com.project.ecommerce_youtube.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pub/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }


    @PostMapping("/createrating")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestParam("id") Long userId) throws UserException {
        try {
            // Assuming you have a way to fetch the user by userId
            User user = userService.findUserById(userId);
            Rating rating = ratingService.createRating(request, user);
            return ResponseEntity.ok(rating);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId) {
        List<Rating> ratings = ratingService.getProductsRating(productId);
        return ResponseEntity.ok(ratings);
    }
}

