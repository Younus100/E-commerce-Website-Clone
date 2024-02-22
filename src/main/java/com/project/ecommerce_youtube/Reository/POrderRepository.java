package com.project.ecommerce_youtube.Reository;

import com.project.ecommerce_youtube.model.POrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface POrderRepository extends JpaRepository<POrder,Long> {

    // Method to find an order by its ID
    POrder findById(long orderId);

    // Method to retrieve all orders
    @Override
    List<POrder> findAll();

    @Override
    void deleteById(Long orderId);   //delete order using order id


}
