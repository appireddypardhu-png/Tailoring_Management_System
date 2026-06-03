package com.project.Tailoring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.Entities.Order;
import com.project.Tailoring.Entities.SubOrder;
import com.project.Tailoring.Service.OrderService;
import com.project.Tailoring.Service.SubOrderService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.Tailoring.DTO.OrderReportDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService ordersService;

    @Autowired
    private SubOrderService subOrderService;

    // SAVE ORDER
    @PostMapping
    public Order saveOrder(@RequestBody Order orders) {
        return ordersService.saveOrder(orders);
    }

    // GET ALL ORDERS
    @GetMapping
    public List<Order> getAllOrders() {
        return ordersService.getAllOrders();
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }
    
    //NEW REPORT API
    @GetMapping("/report")
    public ResponseEntity<?> getOrdersBetweenDates(

            @RequestParam String startDate,

            @RequestParam String endDate

    ) throws Exception {

        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd");

        Date start =
                sdf.parse(startDate);

        Date end =
                sdf.parse(endDate);

        List<OrderReportDTO> orders =
                ordersService.getOrdersBetweenDates(
                        start,
                        end
                );

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "totalOrders",
                orders.size()
        );

        response.put(
                "orders",
                orders
        );

        return ResponseEntity.ok(response);
    }

    // SAVE SUBORDER
    @PostMapping("/suborder")
    public SubOrder saveSubOrder(@RequestBody SubOrder subOrder) {
        return subOrderService.saveSubOrder(subOrder);
    }

    // GET ALL SUBORDERS
    @GetMapping("/suborder")
    public List<SubOrder> getAllSubOrders() {
        return subOrderService.getAllSubOrders();
    }

    // GET SUBORDER BY ID
    @GetMapping("/suborder/{id}")
    public SubOrder getSubOrderById(@PathVariable Long id) {
        return subOrderService.getSubOrderById(id);
    }
}