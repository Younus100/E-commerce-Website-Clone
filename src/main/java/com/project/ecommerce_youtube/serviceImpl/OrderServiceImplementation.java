package com.project.ecommerce_youtube.serviceImpl;

import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.Reository.OrderItemsRepositoty;
import com.project.ecommerce_youtube.Reository.POrderRepository;
import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.*;
import com.project.ecommerce_youtube.service.CartService;
import com.project.ecommerce_youtube.service.OrderService;
import com.project.ecommerce_youtube.service.ProductService;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImplementation implements OrderService {

    private CartRepository cartRepository;
    private CartService caetItemService;
    private ProductService productService;

    private CartService cartService;

    private POrderRepository pOrderRepository;

    private OrderItemsRepositoty orderItemsRepositoty;



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


    @Override
    public POrder findOrderById(Long orderid) throws OrderException {
        return null;
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
    public POrder canceledOrder(Long orderid) throws OrderException {
        return null;
    }

    @Override
    public List<POrder> getAllOrder() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
