package com.project.Tailoring.Service;


import java.util.List;

import com.project.Tailoring.Entities.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);
    
    Customer getCustomerByPhone(String phone);

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}