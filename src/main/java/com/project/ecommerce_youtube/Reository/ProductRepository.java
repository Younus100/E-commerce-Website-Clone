package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category = :category) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:minDiscount IS NULL OR p.discountedPrice >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'discount_highToLow' THEN p.discountedPrice END DESC, " +
            "CASE WHEN :sort = 'discount_lowToHigh' THEN p.discountedPrice END ASC")
    public List<Product> filterProducts(@Param("category") String category,
                                        @Param("minPrice")Integer minPrice,
                                        @Param("maxPrice")Integer maxPrice,
                                        @Param("minDiscount")Integer minDiscount,
                                        @Param("sort")String sort );


    Product findProductById(Long productId);
}
