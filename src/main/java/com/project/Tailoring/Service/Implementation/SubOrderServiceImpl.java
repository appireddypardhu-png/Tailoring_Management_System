package com.project.Tailoring.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Tailoring.Entities.Member;
import com.project.Tailoring.Entities.Order;
import com.project.Tailoring.Entities.SubOrder;

import com.project.Tailoring.Repository.MemberRepository;
import com.project.Tailoring.Repository.OrderRepository;
import com.project.Tailoring.Repository.SubOrderRepository;

import com.project.Tailoring.Service.SubOrderService;

@Service
public class SubOrderServiceImpl implements SubOrderService {

    @Autowired
    private SubOrderRepository subOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public SubOrder saveSubOrder(SubOrder subOrder) {

        // SET ORDER
        if (subOrder.getOrderid() != null) {

            Order orders = orderRepository
                    .findById(subOrder.getOrderid())
                    .orElseThrow(() ->
                        new RuntimeException("Order not found"));

            subOrder.setOrders(orders);
        }

        // SET MEMBER
        if (subOrder.getMemberid() != null) {

            Member member = memberRepository
                    .findById(subOrder.getMemberid())
                    .orElseThrow(() ->
                        new RuntimeException("Member not found"));

            subOrder.setMember(member);
        }

        return subOrderRepository.save(subOrder);
    }

    @Override
    public List<SubOrder> getAllSubOrders() {

        return subOrderRepository.findAll();
    }

    @Override
    public SubOrder getSubOrderById(Long id) {

        return subOrderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SubOrder updateSubOrderStatus(Long subOrderId, String status) {
        SubOrder so = subOrderRepository.findById(subOrderId)
                .orElseThrow(() -> new RuntimeException("SubOrder not found"));
        so.setStatus(status);
        return subOrderRepository.save(so);
    }
}