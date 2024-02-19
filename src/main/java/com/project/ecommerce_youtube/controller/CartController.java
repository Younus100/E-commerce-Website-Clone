package com.project.ecommerce_youtube.controller;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.request.AddItemRequest;
import com.project.ecommerce_youtube.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody User user) {
        Cart createdCart = cartService.createCart(user);
        return ResponseEntity.ok(createdCart);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<String> addCartItem(@PathVariable Long userId, @RequestBody AddItemRequest req) {
        try {
            String message = cartService.addCartItem(userId, req);
            return ResponseEntity.ok(message);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getUserCart(@PathVariable Long userId) {
        Cart cart = cartService.findUserCart(userId);
        return ResponseEntity.ok(cart);
    }
}
