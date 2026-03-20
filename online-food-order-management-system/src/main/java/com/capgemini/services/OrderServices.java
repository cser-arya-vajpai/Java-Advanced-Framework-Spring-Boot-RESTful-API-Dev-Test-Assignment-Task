package com.capgemini.services;

import com.capgemini.model.Order;
import com.capgemini.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository repository;

    public Map<String, Object> createOrder(Order order) {
        Map<String, Object> response = new HashMap<>();

        if (order.getQuantity() <= 0) {
            response.put("message", "Quantity must be greater than 0");
            return response;
        }

        order.setStatus("PLACED");
        Order saved = repository.save(order);

        response.put("message", "Order created successfully");
        response.put("orderId", saved.getOrderId());

        return response;
    }

    public Object getOrder(int id) {
        Optional<Order> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return "Order not found";
        }
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Map<String, String> updateOrder(int id, Order updatedOrder) {
        Map<String, String> response = new HashMap<>();

        Optional<Order> optional = repository.findById(id);

        if (optional.isEmpty()) {
            response.put("message", "Order not found");
            return response;
        }

        Order existing = optional.get();

        if ("CANCELLED".equals(existing.getStatus())) {
            response.put("message", "Cannot update cancelled order");
            return response;
        }

        if (updatedOrder.getQuantity() <= 0) {
            response.put("message", "Quantity must be greater than 0");
            return response;
        }

        existing.setCustomerName(updatedOrder.getCustomerName());
        existing.setFoodItem(updatedOrder.getFoodItem());
        existing.setQuantity(updatedOrder.getQuantity());

        repository.save(existing);

        response.put("message", "Order updated successfully");
        return response;
    }

    public Map<String, String> cancelOrder(int id) {
        Map<String, String> response = new HashMap<>();

        Optional<Order> optional = repository.findById(id);

        if (optional.isEmpty()) {
            response.put("message", "Order not found");
            return response;
        }

        Order order = optional.get();
        order.setStatus("CANCELLED");

        repository.save(order);

        response.put("message", "Order cancelled successfully");
        return response;
    }
}