package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer); //POST

    List<Customer> findAllCustomers();

    void delete(long id); //DELETE

    Customer update(Customer customer, long id); //PUT, PATCH

    void deleteAllCustomers();
}
