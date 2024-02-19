package com.project.ecommerce_youtube.request;

import com.project.ecommerce_youtube.model.CartItems;

public class UpdateCartItemRequest {
    private Long userId;
    private Long cartItemId;
    private CartItems cartItem;

    public UpdateCartItemRequest() {
    }

    public UpdateCartItemRequest(Long userId, Long cartItemId, CartItems cartItem) {
        this.userId = userId;
        this.cartItemId = cartItemId;
        this.cartItem = cartItem;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public CartItems getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItems cartItem) {
        this.cartItem = cartItem;
    }
}

