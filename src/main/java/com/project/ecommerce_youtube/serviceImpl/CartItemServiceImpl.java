package com.project.ecommerce_youtube.serviceImpl;

import com.project.ecommerce_youtube.Reository.CartItemRepository;
import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.exception.CartItemException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.exception.UserException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.model.Product;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.AddItemRequest;
import com.project.ecommerce_youtube.service.CartItemService;
import com.project.ecommerce_youtube.service.ProductService;
import com.project.ecommerce_youtube.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository ;
    private UserService userService;
    private CartRepository cartRepository;
    private final ProductService productService;


    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository,ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.productService = productService;
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

    @Override
    public String addCartItem(Long userId, AddItemRequest addItemRequest) throws CartItemException, UserException, ProductException {
        // Check if the user exists
        User user = userService.findUserById(userId);

        // Fetch the product by its ID
        Product product = productService.getProductById(addItemRequest.getProductId());

        // Create a new CartItem
        CartItems cartItem = new CartItems();
        cartItem.setUserId(userId); // Set the user ID
        cartItem.setProduct(product);
        cartItem.setSize(addItemRequest.getSize());
        cartItem.setQuantity(addItemRequest.getQuantity());
        cartItem.setPrice(product.getPrice() * addItemRequest.getQuantity());
        cartItem.setDiscountedPrice(product.getDiscountedPrice() * addItemRequest.getQuantity());

        // Save the CartItem
        cartItemRepository.save(cartItem);

        return "Cart item added successfully";
    }



}

