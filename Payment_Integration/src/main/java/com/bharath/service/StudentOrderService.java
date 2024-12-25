package com.bharath.service;

import java.util.Map;

import com.bharath.entity.StudentOrder;

public interface StudentOrderService {

	
	
	public StudentOrder createOrder(StudentOrder stuOrder) throws Exception;
	public StudentOrder updateOrder(Map<String,String> responsePayLoad);
}
