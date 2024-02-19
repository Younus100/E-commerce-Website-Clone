package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.model.POrder;
import com.project.ecommerce_youtube.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private final OrderService OrderServiceImplementation;

    public AdminOrderController(OrderService orderServiceImplementation) {
        this.OrderServiceImplementation = orderServiceImplementation;

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<POrder> getOrderById(@PathVariable Long orderId) {
        try {
            POrder order = OrderServiceImplementation.findOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<POrder>> getAllOrders() {
        List<POrder> orders = OrderServiceImplementation.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/place/{orderId}")
    public ResponseEntity<POrder> placeOrder(@PathVariable Long orderId) {
        try {
            POrder placedOrder = OrderServiceImplementation.placedOrder(orderId);
            return ResponseEntity.ok(placedOrder);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

