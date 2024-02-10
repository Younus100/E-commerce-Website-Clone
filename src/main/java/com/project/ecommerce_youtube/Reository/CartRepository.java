package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("Select c from Cart c Where c.user.id=:userId")
    Cart findByUserId(@Param("userId") Long userId);
}
