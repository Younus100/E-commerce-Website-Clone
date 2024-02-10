package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("Select r from Rating r Where r.product.id=:productId")
    List<Rating> finfRatingByProducts(@Param("productId")Long productId);
}
