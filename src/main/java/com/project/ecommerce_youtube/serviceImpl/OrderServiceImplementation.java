package com.project.ecommerce_youtube.serviceImpl;

import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.Reository.OrderItemsRepository;
import com.project.ecommerce_youtube.Reository.POrderRepository;
import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.*;
import com.project.ecommerce_youtube.service.CartService;
import com.project.ecommerce_youtube.service.OrderService;
import com.project.ecommerce_youtube.service.ProductService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImplementation implements OrderService {

    private CartRepository cartRepository;
    private CartService cartItemService;
    private ProductService productService;

    private CartService cartService;

    private POrderRepository pOrderRepository;

    private OrderItemsRepository orderItemsRepository;

//    @Autowired
//    public void OrderService(POrderRepository orderRepository) {
//        this.pOrderRepository = orderRepository;
//    }



    @Override
    public POrder createOrder(User user, Address address) throws OrderException, ProductException {
        // Retrieve cart items for the user
        Cart cart = cartService.findUserCart(user.getId());

        // Ensure cart items are not empty
        if (cart.getCartItems().isEmpty()) {
            throw new OrderException("Cart is empty");
        }

        // Create a new order object
        POrder order = new POrder();
        order.setUser(user);
        order.setShippingAddress(address);
        order.setOrderStatus("PENDING"); // Assuming OrderStatus is an enum representing order status

        List<OrderItems> orderItemsList = new ArrayList<>();

        for (CartItems cartItem : cart.getCartItems()) {
            Product product = productService.getProductById(cartItem.getProduct().getId());

            // Check if sufficient quantity is available
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new OrderException("Insufficient quantity available for product: " + product.getBrand());
            }

            // Decrement the product quantity
            int remainingQuantity = product.getQuantity() - cartItem.getQuantity();
            product.setQuantity(remainingQuantity);

            // Create an order item
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice()); // Assuming the price is directly available from the product
            orderItem.setPOrder(order);

            orderItemsList.add(orderItem);

            // Update the product in the database
            productService.updateProductP(product);
        }

        // Set order items to the order
        order.setOrderItemsList(orderItemsList);

        // Save the order
        pOrderRepository.save(order);

        // Clear the user's cart
        cartService.clearCart(user.getId());

        return order;
    }


    public POrder findOrderById(Long orderId) throws OrderException {
        Optional<POrder> orderOptional = pOrderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            throw new OrderException("Order not found with ID: " + orderId);
        }
    }

    @Override
    public List<POrder> usersOrderHistory(Long userid) {
        return null;
    }

    @Override
    public POrder placedOrder(Long orderid) throws OrderException {
        return null;
    }

    @Override
    public POrder confirmedOrder(Long orderid) throws OrderException {
        return null;
    }

    @Override
    public POrder shilledOrder(Long orderid) throws OrderException {
        return null;
    }

    @Override
    public POrder deliveredOrder(Long orderid) throws OrderException {
        return null;
    }
    @Override
    public POrder canceledOrder(Long orderId) throws OrderException {
      return null;
    }

    @Override
    public List<POrder> getAllOrder() {
        return pOrderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(Long orderId) throws OrderException {
        // Retrieve the order by ID
        Optional<POrder> orderOptional = pOrderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            POrder order = orderOptional.get();

            // Updating the order, Example: Increase product quantity by one for each item in the order
            List<OrderItems> orderItems = orderItemsRepository.findAll();
            for (OrderItems orderItem : orderItems) {
                Product product = orderItem.getProduct();
                // Adjust product quantity in stock
                int updatedQuantity = product.getQuantity() + 1; // Increase by one
                product.setQuantity(updatedQuantity);
                // Update the product in the database
                productService.updateProductP(product);
            }

            // Delete the order
            pOrderRepository.deleteById(orderId);

        } else {
            throw new OrderException("Order not found with ID: " + orderId);
        }
    }
}

