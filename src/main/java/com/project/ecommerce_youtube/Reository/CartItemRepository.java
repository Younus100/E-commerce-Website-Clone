package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Cart;
import com.project.ecommerce_youtube.model.CartItems;
import com.project.ecommerce_youtube.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository  extends JpaRepository<CartItems,Long> {
    @Query("SELECT ci FROM CartItems ci WHERE ci.cart = :cart AND " +
            "ci.product = :product AND ci.size = :size AND ci.userId = :userId")
    CartItems isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product,
                              @Param("size") String size, @Param("userId") Long userId);


}


