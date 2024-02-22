package com.project.ecommerce_youtube.request;

import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;

import java.util.HashSet;
import java.util.Set;

public class AddToCartRequest {
    private Long userId;
    private Long productId;
    private Cart cart;
    private CartItems cartItems;
    private int quantity;
    private Double price;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CartItems getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItems cartItems) {
        this.cartItems = cartItems;
    }
}
