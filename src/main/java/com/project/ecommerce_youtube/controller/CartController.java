package com.project.ecommerce_youtube.controller;

import org.springframework.web.bind.annotation.*;


import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.request.AddItemRequest;
import com.project.ecommerce_youtube.service.CartService;
import com.project.ecommerce_youtube.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/cart")
public class CartController {

    private final CartService cartService;

    private UserService userService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-item/{userId}")
    public ResponseEntity<String> addCartItemToCart(@RequestBody AddItemRequest req) {
        try {

            String result = cartService.addCartItem(getUserIdFromAuthentication(), req);
            return ResponseEntity.ok(result);
        } catch (ProductException | UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private Long getUserIdFromAuthentication() throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Extract user ID from your custom UserDetailsService or UserDetails implementation
        // For example, assuming UserDetails is a custom class implementing UserDetails
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email =  userDetails.getUsername(); // Adjust this based on your UserDetails implementation
        return userService.findUserByEmail(email);
    }

    @GetMapping("/getcart")
    public ResponseEntity<Cart> findUserCart() {
        try {
            Cart result = cartService.findUserCart(getUserIdFromAuthentication());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
