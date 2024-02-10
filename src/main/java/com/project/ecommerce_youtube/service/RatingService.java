package com.project.ecommerce_youtube.service;


import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Rating;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.RatingRequest;

import java.util.List;

public interface RatingService {
     Rating createRating(RatingRequest req, User user) throws ProductException;
     List<Rating> getProductsRating(Long productId);

}
