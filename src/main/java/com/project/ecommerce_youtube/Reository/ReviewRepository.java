package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Rating;
import com.project.ecommerce_youtube.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("Select r from Review r Where r.product.id=:productId")
    List<Review> finfReviewByProducts(@Param("productId")Long productId);
}
