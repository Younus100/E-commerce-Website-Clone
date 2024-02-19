package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.request.AddItemRequest;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.User;

public interface CartService {

    Cart createCart(User user);  //idhar cart user lega ki cart lega
    String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    Cart findUserCart(Long userId);

}
