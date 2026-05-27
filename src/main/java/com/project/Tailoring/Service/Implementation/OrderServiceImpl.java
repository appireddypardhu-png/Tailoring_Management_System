package com.project.Tailoring.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Entities.Order;
import com.project.Tailoring.Repository.CustomerRepository;
import com.project.Tailoring.Repository.OrderRepository;
import com.project.Tailoring.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Order saveOrder(Order orders) {

        if (orders.getCustomerid() != null) {

            Customer customer = customerRepository
                    .findById(orders.getCustomerid())
                    .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

            orders.setCustomer(customer);
        }

        return orderRepository.save(orders);
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id).orElse(null);
    }
}