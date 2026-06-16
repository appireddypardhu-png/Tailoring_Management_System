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
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.http.ResponseEntity;
import java.util.Map;

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

    // GET ORDERS FOR A YEAR
    @GetMapping("/year/{year}")
    public ResponseEntity<?> getOrdersByYear(@PathVariable int year) throws Exception {
        Calendar startCal = new GregorianCalendar();
        startCal.clear();
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.MONTH, Calendar.JANUARY);
        startCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = new GregorianCalendar();
        endCal.clear();
        endCal.set(Calendar.YEAR, year);
        endCal.set(Calendar.MONTH, Calendar.DECEMBER);
        endCal.set(Calendar.DAY_OF_MONTH, 31);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);

        Date start = startCal.getTime();
        Date end = endCal.getTime();

        List<Order> orders = ordersService.getOrdersBetweenDates(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", orders.size());
        response.put("orders", orders);

        return ResponseEntity.ok(response);
    }

    // GET ORDERS FOR A GIVEN MONTH IN A YEAR (month: 1-12)
    @GetMapping("/year/{year}/month/{month}")
    public ResponseEntity<?> getOrdersByMonthOfYear(@PathVariable int year, @PathVariable int month) throws Exception {
        if (month < 1 || month > 12) {
            return ResponseEntity.badRequest().body(Map.of("error", "month must be between 1 and 12"));
        }

        Calendar startCal = new GregorianCalendar();
        startCal.clear();
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.MONTH, month - 1);
        startCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = new GregorianCalendar();
        endCal.clear();
        endCal.set(Calendar.YEAR, year);
        endCal.set(Calendar.MONTH, month - 1);
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);

        Date start = startCal.getTime();
        Date end = endCal.getTime();

        List<Order> orders = ordersService.getOrdersBetweenDates(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("totalOrders", orders.size());
        response.put("orders", orders);

        return ResponseEntity.ok(response);
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }

    // GET ORDERS BY CUSTOMER ID
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return ordersService.getOrdersByCustomer(customerId);
    }

    // UPDATE ORDER STATUS
    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.getOrDefault("orderStatus", body.get("status"));
        return ordersService.updateOrderStatus(id, status);
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

        List<Order> orders =
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

    // UPDATE SUBORDER STATUS
    @PatchMapping("/suborder/{id}/status")
    public SubOrder updateSubOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.getOrDefault("status", body.get("subOrderStatus"));
        return subOrderService.updateSubOrderStatus(id, status);
    }
}