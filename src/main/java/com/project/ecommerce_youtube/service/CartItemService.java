package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.exception.CartItemException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.request.AddItemRequest;

public interface CartItemService {
    CartItems createcardItem(CartItems cartItems);

    CartItems updateCartItem(Long userId,Long id,CartItems cartItems) throws CartItemException, UserException;

    CartItems isCartItemExist(Cart cart, Product product,String size,Long userId);

    void  removeCartItem(Long userId,Long cartitemid) throws CartItemException, UserException;

    CartItems findCartItemById(Long cartitemid) throws CartItemException;
    String addCartItem(Long userId, AddItemRequest addItemRequest) throws CartItemException, UserException, ProductException;

}
