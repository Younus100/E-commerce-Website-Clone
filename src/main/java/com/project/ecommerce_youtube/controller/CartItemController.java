package com.project.ecommerce_youtube.controller;

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


