package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order); //POST

    List<Order> findAllOrders();

    void delete(long id); //DELETE

    Order update(Order order, long id); //PUT, PATCH

    void deleteAllOrders();
}
