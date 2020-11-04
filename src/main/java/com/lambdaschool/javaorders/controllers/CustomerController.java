package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity<?> listAllCustomers(){
        List<Customer> myCustomers = customerService.findAllCustomers();

        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerByCustomerCode(@PathVariable long custcode){
        customerService.delete(custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateCustomerByCustomerCode(@PathVariable long custcode, @Valid @RequestBody Customer updateCustomer){
        customerService.update(updateCustomer, custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/customer/{custcode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> replaceCustomer(@PathVariable long custcode, @Valid @RequestBody Customer customer){
        customer.setCustcode(custcode);

        customer = customerService.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0);

        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();

        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
    }
}
