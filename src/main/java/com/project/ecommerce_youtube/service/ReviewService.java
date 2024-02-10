package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Review;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.ReviewRequest;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    Review createReview(ReviewRequest req, User user) throws ProductException;
    List<Review> getAllReview(Long productId);
}
