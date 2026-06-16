package com.project.Tailoring.Service.Implementation;

import java.util.List;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Tailoring.Entities.SubOrder;
import com.project.Tailoring.Entities.Customer;
import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Entities.Order;
import com.project.Tailoring.Repository.CustomerRepository;
import com.project.Tailoring.Repository.OrderRepository;
import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public Order saveOrder(Order orders) {

        if (orders.getCustomerid() != null) {

            Customer customer = customerRepository
                    .findById(orders.getCustomerid())
                    .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

            orders.setCustomer(customer);
        }

        // Ensure suborders are linked to parent Order and members are resolved
        if (orders.getSubOrders() != null) {
            for (var so : orders.getSubOrders()) {
                // set back-reference so cascade works
                so.setOrders(orders);

                if (so.getMemberid() != null) {
                    Member member = memberRepository
                            .findById(so.getMemberid())
                            .orElseThrow(() -> new RuntimeException("Member not found"));
                    so.setMember(member);
                }
            }
        }

        // If orderdate is not provided, set to current date
        if (orders.getOrderdate() == null) {
            orders.setOrderdate(new Date());
        }

        // If orderprice is not provided, compute from subOrders (price * quantity)
        if (orders.getOrderprice() == null || orders.getOrderprice() == 0.0) {
            Double total = 0.0;
            if (orders.getSubOrders() != null) {
                for (var so : orders.getSubOrders()) {
                    Double p = so.getPrice() == null ? 0.0 : so.getPrice();
                    Integer q = so.getQuantity() == null ? 0 : so.getQuantity();
                    total += p * q;
                }
            }
            orders.setOrderprice(total);
        }

        return orderRepository.save(orders);
    }

    
    @Override
    public List<Order> getOrdersBetweenDates(
            Date startDate,
            Date endDate
    ) {

        return orderRepository.findOrdersBetweenDates(
                startDate,
                endDate
        );
    }
    
    @Override
    public List<Order> getAllOrders() {
        // Use fetch-join query to load customers, suborders and members in one go
        List<Order> orders = orderRepository.findAllWithCustomerAndSubOrdersAndMembersOrderByOrderidDesc();

        // Return at most 20 latest records
        if (orders == null) return List.of();
        return orders.size() > 20 ? orders.subList(0, 20) : orders;
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerid) {
        // Use fetch-join query to load suborders and members as well
        return orderRepository.findByCustomerIdWithSubOrdersAndMembersOrderByOrderidDesc(customerid);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(status);

        // If the order is being marked completed, also mark all its suborders completed
        if (status != null && status.equalsIgnoreCase("completed")) {
            if (order.getSubOrders() != null) {
                for (SubOrder so : order.getSubOrders()) {
                    so.setStatus(status);
                }
            }
        }

        return orderRepository.save(order);
    }
}