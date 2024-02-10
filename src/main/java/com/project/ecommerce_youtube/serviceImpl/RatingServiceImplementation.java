package com.project.ecommerce_youtube.serviceImpl;

import com.project.ecommerce_youtube.Reository.RatingRepository;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.model.Rating;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.RatingRequest;
import com.project.ecommerce_youtube.service.ProductService;
import com.project.ecommerce_youtube.service.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {

    private RatingRepository ratingRepository;

    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.getProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.finfRatingByProducts(productId);
    }
}
