package com.project.ecommerce_youtube.controller;

import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Address;
import com.project.ecommerce_youtube.model.POrder;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<POrder> createOrder(@RequestBody User user, @RequestBody Address address) throws OrderException, ProductException {
        POrder order = orderService.createOrder(user, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<POrder> getOrderById(@PathVariable Long orderId) {
        try {
            POrder order = orderService.findOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<POrder>> getUserOrderHistory(@PathVariable Long userId) {
        List<POrder> orders = orderService.usersOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/place/{orderId}")
    public ResponseEntity<POrder> placeOrder(@PathVariable Long orderId) {
        try {
            POrder order = orderService.placedOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<POrder> confirmOrder(@PathVariable Long orderId) {
        try {
            POrder order = orderService.confirmedOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/ship/{orderId}")
    public ResponseEntity<POrder> shipOrder(@PathVariable Long orderId) {
        try {
            POrder order = orderService.shilledOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/deliver/{orderId}")
    public ResponseEntity<POrder> deliverOrder(@PathVariable Long orderId) {
        try {
            POrder order = orderService.deliveredOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<POrder> cancelOrder(@PathVariable Long orderId) {
        try {
            POrder order = orderService.canceledOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<POrder>> getAllOrders() {
        List<POrder> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }
}
