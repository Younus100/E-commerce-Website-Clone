package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.CartItemException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<CartItems> createCartItem(@RequestBody CartItems cartItems) {
        CartItems createdCartItem = cartItemService.createcardItem(cartItems);
        return ResponseEntity.ok(createdCartItem);
    }

   //nahi samaj raha hai ye kaise karu
}


