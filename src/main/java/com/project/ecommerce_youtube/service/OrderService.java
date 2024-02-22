package com.project.ecommerce_youtube.service;

import com.project.ecommerce_youtube.exception.OrderException;
import com.project.ecommerce_youtube.exception.ProductException;
import com.project.ecommerce_youtube.model.Address;
import com.project.ecommerce_youtube.model.User;
import com.project.ecommerce_youtube.model.POrder;


import java.util.List;

public interface OrderService {
    POrder createOrder(User user, Address address) throws OrderException, ProductException;

    POrder findOrderById(Long orderid) throws OrderException;

    List<POrder> usersOrderHistory(Long userid);

    POrder placedOrder(Long orderid) throws OrderException;

    POrder confirmedOrder(Long orderid) throws OrderException;

    POrder shilledOrder(Long orderid) throws  OrderException;
    POrder deliveredOrder(Long orderid) throws OrderException;
    POrder canceledOrder(Long orderid) throws  OrderException;
    List<POrder> getAllOrder();
    void deleteOrder(Long orderId) throws OrderException;


}
