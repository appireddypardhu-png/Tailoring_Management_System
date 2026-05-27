package com.project.Tailoring.Service;

import java.util.List;

import com.project.Tailoring.Entities.SubOrder;

public interface SubOrderService {

    SubOrder saveSubOrder(SubOrder subOrder);

    List<SubOrder> getAllSubOrders();

    SubOrder getSubOrderById(Long id);
}