package com.bharath.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.entity.StudentOrder;

public interface StudentOrderRepositary extends JpaRepository<StudentOrder, Integer> {

	
	public StudentOrder findByRazorpayOrderId(String orderId);
}
