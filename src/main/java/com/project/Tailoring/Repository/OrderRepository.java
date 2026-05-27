package com.project.Tailoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Tailoring.Entities.Order;

public interface OrderRepository
extends JpaRepository<Order, Long> {

}