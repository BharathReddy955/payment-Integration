package com.bharath.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bharath.entity.StudentOrder;
import com.bharath.service.StudentOrderService;

@Controller
public class StudentOrderController {

	@Autowired
	private StudentOrderService ser;
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping(value="/create-order",produces = "application/json")
	@ResponseBody
	public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrder studentOrder) throws Exception{
		
		StudentOrder createOrder=ser.createOrder(studentOrder);
		return new ResponseEntity<StudentOrder>(createOrder,HttpStatus.CREATED);
	}
	
	@PostMapping("/handle-payment-callback")
	public String hnadlePaymentCallback(@RequestParam Map<String,String> resPayLoad) {
	ser.updateOrder(resPayLoad);
	return "success";
	}

}


