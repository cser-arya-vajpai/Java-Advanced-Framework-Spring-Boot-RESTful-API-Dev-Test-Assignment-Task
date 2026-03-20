package com.capgemini.controller;

import com.capgemini.model.Order;
import com.capgemini.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices service;

    @PostMapping
    public Object createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping("/{id}")
    public Object getOrder(@PathVariable int id) {
        return service.getOrder(id);
    }

    @GetMapping
    public Object getAllOrders() {
        return service.getAllOrders();
    }

    @PutMapping("/{id}")
    public Object updateOrder(@PathVariable int id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public Object cancelOrder(@PathVariable int id) {
        return service.cancelOrder(id);
    }
}