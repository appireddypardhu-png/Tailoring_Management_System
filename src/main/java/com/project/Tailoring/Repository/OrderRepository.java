package com.project.Tailoring.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.Tailoring.DTO.OrderReportDTO;
import com.project.Tailoring.Entities.Order;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    @Query("""
        SELECT new com.project.Tailoring.DTO.OrderReportDTO(
            o.orderid,
            c.cname
        )
        FROM Order o
        JOIN o.customer c
        WHERE o.orderdate BETWEEN :startDate AND :endDate
        ORDER BY o.orderdate DESC
    """)
    List<OrderReportDTO> findOrdersBetweenDates(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    // Find all orders for a given customer id
    List<Order> findByCustomerCustomeridOrderByOrderdateDesc(Long customerid);

    // Find all orders ordered by date desc
    List<Order> findAllByOrderByOrderdateDesc();
}