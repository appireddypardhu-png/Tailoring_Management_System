package com.project.Tailoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // CREATE
    @PostMapping
    public Customer saveCustomer(
            @RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }

    // GET ALL
    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Customer getCustomerById(
            @PathVariable Long id) {

        return customerService.getCustomerById(id);
    }
    // GET BY PHONE
    @GetMapping("/phone/{phone}")
    public Customer getCustomerByPhone(@PathVariable String phone) {

        return customerService.getCustomerByPhone(phone);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }
}
