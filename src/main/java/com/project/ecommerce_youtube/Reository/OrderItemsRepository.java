package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
    List<OrderItems> findAll(); // Method to retrieve all order items

}
