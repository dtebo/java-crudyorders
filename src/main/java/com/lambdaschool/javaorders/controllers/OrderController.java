package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders(){
        List<Order> myOrders = orderService.findAllOrders();

        return new ResponseEntity<>(myOrders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{ordernum}")
    public ResponseEntity<?> deleteOrderByOrderNumber(@PathVariable long ordernum){
        orderService.delete(ordernum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/order/{ordernum}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateOrderByOrderNumber(@PathVariable long ordernum, @Valid @RequestBody Order updateOrder){
        updateOrder.setOrdernum(ordernum);

        updateOrder = orderService.save(updateOrder);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }
}
