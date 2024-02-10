package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.request.RatingRequest;
import com.project.ecommerce_youtube.request.ReviewRequest;
import com.project.ecommerce_youtube.service.ReviewServiceImplementation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class RatingController {
    ReviewServiceImplementation reviewServiceImplementation;

    getReviewUsr(@RequestBody User,)
    {

    }
    addReviewUSer(@RequestBody User,@RequestBody Review review) {
        ReviewRequest request = RatingRequest();
        request.setReview(review.getReview());

        reviewServiceImplementation.createReview ( request ,user)
    }
}
