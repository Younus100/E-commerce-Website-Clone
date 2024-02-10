package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {

    private CartRepository cartRepository;

    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImplementation(CartItemService cartItemService, ProductService productService, CartRepository cartRepository1) {
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.cartRepository = cartRepository1;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(req.getProductId());
        CartItems isPresent =cartItemService.isCartItemExist(cart,product,req.getSize(),userId);
        if(isPresent==null) {
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setCart(cart);
            cartItems.setQuantity(req.getQuantity());
            cartItems.setUserId(userId);
            int price = req.getQuantity()*product.getDiscountedPrice();
            cartItems.setSize(req.getSize());

            CartItems createdCartitem =  cartItemService.createcardItem(cartItems);
            cart.getCartItems().add(createdCartitem);
        }

        return "Item Add toCart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        int discountedPrice = 0;
        int totalItem = 0;
        for(CartItems cartItems:cart.getCartItems()){
            totalPrice += cartItems.getPrice();
            discountedPrice += cartItems.getDiscountedPrice();
            totalItem +=  cartItems.getQuantity();
        }
        cart.setTotalDiscountedPrice(discountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-discountedPrice);

        return cartRepository.save(cart);
    }
}
