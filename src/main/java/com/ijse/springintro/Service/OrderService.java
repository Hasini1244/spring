package com.ijse.springintro.Service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springintro.Entity.Order;



@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
}