package com.project.Tailoring.Service;

import java.util.Date;
import java.util.List;

import com.project.Tailoring.DTO.OrderReportDTO;
import com.project.Tailoring.Entities.Order;

public interface OrderService {

    Order saveOrder(Order orders);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    List<OrderReportDTO> getOrdersBetweenDates(
            Date startDate,
            Date endDate
    );
}