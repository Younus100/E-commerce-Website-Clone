package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepositoty extends JpaRepository<OrderItems,Long> {
}
