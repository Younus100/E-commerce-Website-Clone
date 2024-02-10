package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.Reository.CartRepository;
import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.model.Address;
import com.project.ecommerce_youtube.model.POrder;
import com.project.ecommerce_youtube.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    private CartRepository cartRepository;
    private CartService caetItemService;
    private ProductService productService;

    public OrderServiceImplementation(CartRepository cartRepository, CartService caetItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.caetItemService = caetItemService;
        this.productService = productService;
    }

    @Override
    public POrder createOrder(User user, Address address) {
        return null;
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
