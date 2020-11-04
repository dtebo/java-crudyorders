package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.repositories.CustomerRepository;
import com.lambdaschool.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerservice")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0){
            customerRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found!"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        for(Order o : customer.getOrders()){
            Order newOrder = orderRepository.findById(o.getOrdernum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + o.getOrdernum() + " Not Found!"));
        }

        return customerRepository.save(newCustomer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        customerRepository.findAll()
                .iterator()
                .forEachRemaining(customerList::add);

        return customerList;
    }

    @Transactional
    @Override
    public void delete(long id){

    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer updateCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found!"));

        if(customer.getCustname() != null){
            updateCustomer.setCustname(customer.getCustname());
        }

        if(customer.getCustcity() != null){
            updateCustomer.setCustcity(customer.getCustcity());
        }

        if(customer.getWorkingarea() != null){
            updateCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if(customer.getCustcountry() != null){
            updateCustomer.setCustcountry(customer.getCustcountry());
        }

        if(customer.getGrade() != null){
            updateCustomer.setGrade(customer.getGrade());
        }

        if(customer.getOpeningamt() != 0.0){
            updateCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if(customer.getReceiveamt() != 0.0){
            updateCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if(customer.getPaymentamt() != 0.0){
            updateCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if(customer.getOutstandingamt() != 0.0){
            updateCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if(customer.getPhone() != null){
            updateCustomer.setPhone(customer.getPhone());
        }

        if(customer.getAgent() != null){
            updateCustomer.setAgent(customer.getAgent());
        }

        if(customer.getOrders().size() > 0){
            updateCustomer.getOrders().clear();

            for(Order o : customer.getOrders()){
                Order order = new Order();
                order.setOrdamount(o.getOrdamount());
                order.setAdvanceamount(o.getAdvanceamount());
                order.setCustomer(o.getCustomer());
                order.setOrderdescription(o.getOrderdescription());

                updateCustomer.getOrders().add(order);
            }
        }

        return customerRepository.save(updateCustomer);
    }

    @Transactional
    @Override
    public void deleteAllCustomers() {

    }
}
