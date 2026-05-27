package com.project.Tailoring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Tailoring.Entities.Customer;


public interface CustomerRepository 
	extends JpaRepository<Customer, Long> {
	Customer findByCphone(String cphone);
	}
