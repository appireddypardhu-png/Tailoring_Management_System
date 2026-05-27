package com.project.Tailoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Tailoring.Entities.SubOrder;

public interface SubOrderRepository
extends JpaRepository<SubOrder, Long> {

}