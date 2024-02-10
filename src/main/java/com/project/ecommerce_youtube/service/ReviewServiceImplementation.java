package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.Reository.ReviewRepository;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.model.Review;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReviewServiceImplementation implements  ReviewService {


    private ReviewRepository reviewRepository;
    private ProductService productService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product =productService.getProductById(req.getProductId());
        Review review = new Review();
        review.setReview(req.getReview());
        review.setUser(user);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.finfReviewByProducts(productId);
    }
}
