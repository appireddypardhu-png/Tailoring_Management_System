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
        ORDER BY o.orderid DESC
    """)
    List<OrderReportDTO> findOrdersBetweenDates(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    // Find all orders for a given customer id
    List<Order> findByCustomerCustomeridOrderByOrderidDesc(Long customerid);

    // Find all orders ordered by id desc
    List<Order> findAllByOrderByOrderidDesc();

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.subOrders so LEFT JOIN FETCH so.member m LEFT JOIN FETCH o.customer c ORDER BY o.orderid DESC")
    List<Order> findAllWithCustomerAndSubOrdersAndMembersOrderByOrderidDesc();

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.subOrders so LEFT JOIN FETCH so.member m LEFT JOIN FETCH o.customer c WHERE c.customerid = :customerid ORDER BY o.orderid DESC")
    List<Order> findByCustomerIdWithSubOrdersAndMembersOrderByOrderidDesc(@Param("customerid") Long customerid);
}