package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.CartItemException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.request.AddItemRequest;
import com.project.ecommerce_youtube.request.AddToCartRequest;
import com.project.ecommerce_youtube.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/auth/cart/add-to-cart")
    public ResponseEntity<CartItems> createCartItem(@RequestBody AddToCartRequest addToCartRequest) {
        CartItems createdCartItem = cartItemService.createcardItem(addToCartRequest.getCartItems());
        return ResponseEntity.ok(createdCartItem);
    }

    @PostMapping("/auth/cart/add-item")
    public ResponseEntity<String> addCartItemToCart(@RequestBody AddItemRequest addItemRequest) throws ProductException {
        try {
            String result = cartItemService.addCartItem(addItemRequest.getUserId(), addItemRequest);
            return ResponseEntity.ok(result);
        } catch (CartItemException | UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

////create cart item

