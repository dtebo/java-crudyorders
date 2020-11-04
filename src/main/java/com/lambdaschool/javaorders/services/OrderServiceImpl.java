package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderservice")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if(order.getOrdernum() != 0){
            orderRepository.findById(order.getOrdernum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdernum() + " Not Found!"));

            newOrder.setOrdernum(order.getOrdernum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        if(order.getPayments().size() > 0){
            newOrder.getPayments().clear();

            for(Payment p : order.getPayments()){
                Payment newPayment = new Payment();
                newPayment.setPaymentid(p.getPaymentid());

                newOrder.getPayments().add(newPayment);
            }
        }
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> myOrders = new ArrayList<>();

        orderRepository.findAll()
                .iterator()
                .forEachRemaining(myOrders::add);

        return myOrders;
    }

    @Transactional
    @Override
    public void delete(long id) {

    }

    @Transactional
    @Override
    public Order update(Order order, long id) {
        Order updateOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdernum() + " Not Found!"));

        if(order.getOrdamount() != 0.0){
            updateOrder.setOrdamount(order.getOrdamount());
        }

        if(order.getAdvanceamount() != 0.0){
            updateOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if(order.getCustomer() != null){
            updateOrder.setCustomer(order.getCustomer());
        }

        if(order.getOrderdescription() != null){
            updateOrder.setOrderdescription(order.getOrderdescription());
        }

        return orderRepository.save(updateOrder);
    }

    @Transactional
    @Override
    public void deleteAllOrders() {

    }
}
