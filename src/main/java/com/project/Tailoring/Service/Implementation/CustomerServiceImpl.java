package com.project.Tailoring.Service.Implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Repository.CustomerRepository;
import com.project.Tailoring.Service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    
    @Override
    public Customer getCustomerByPhone(String phone) {

        return customerRepository.findByCphone(phone);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer =
                customerRepository.findById(id).orElse(null);

        if (existingCustomer != null) {

            existingCustomer.setCname(customer.getCname());
            existingCustomer.setCphone(customer.getCphone());

            return customerRepository.save(existingCustomer);
        }

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}