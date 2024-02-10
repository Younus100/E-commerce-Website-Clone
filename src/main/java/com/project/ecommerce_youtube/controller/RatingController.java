package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Rating;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.RatingRequest;
import com.project.ecommerce_youtube.service.RatingService;
import com.project.ecommerce_youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private final RatingService ratingService;
    @Autowired
    private final UserService userService;

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest ratingRequest) {
        try {
            User user = new User();
            user.setId(1L);
            // Call the rating service to create a new rating
            Rating rating = ratingService.createRating(ratingRequest, user);
            return ResponseEntity.ok(rating);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
