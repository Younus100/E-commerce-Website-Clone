package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.Reository.CartItemRepository;
import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.exception.CartItemException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService {
    private CartItemRepository cartItemRepository ;
    private UserService userService;
    private CartRepository cartRepository;

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItems createcardItem(CartItems cartItems) {
        cartItems.setQuantity(1);
        cartItems.setPrice(cartItems.getProduct().getPrice()*cartItems.getQuantity());
        cartItems.setDiscountedPrice(cartItems.getProduct().getDiscountedPrice()*cartItems.getQuantity());

        CartItems createdcartItem=cartItemRepository.save(cartItems);
        return createdcartItem;
    }

    @Override
    public CartItems updateCartItem(Long userId, Long id, CartItems cartItems) throws CartItemException, UserException {
        CartItems item = findCartItemById(id);
        User user =userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)){
            item.setQuantity(cartItems.getQuantity());
            item.setPrice(cartItems.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        } else {
            throw  new CartItemException("Cant update cart item for differnet userid");
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItems isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItems cartItems = cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItems;
    }

    @Override
    public void removeCartItem(Long userId, Long cartitemid) throws CartItemException, UserException {
        CartItems cartItems=findCartItemById(cartitemid);
        User user =userService.findUserById(cartItems.getUserId());

        User requser =userService.findUserById(userId);

        if(user.getId().equals(requser.getId())){
            cartItemRepository.deleteById(cartitemid);
        } else {
            throw new UserException("you  cant remove other user it");
        }
    }

    @Override
    public CartItems findCartItemById(Long cartitemid) throws CartItemException {
        Optional<CartItems> cartItem = cartItemRepository.findById(cartitemid);
        if(!cartItem.isPresent()){
            throw new CartItemException("No Cart Item Present");
        }

        return cartItem.get();
    }
}
