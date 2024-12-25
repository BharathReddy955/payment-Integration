package com.bharath.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bharath.entity.StudentOrder;
import com.bharath.repositary.StudentOrderRepositary;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class StudentOrderServiceImpl implements StudentOrderService {

	@Autowired
	private StudentOrderRepositary repo;
	
	@Value("${razorpay.key.id}")
	private  String razorPayKey;
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;

	@Override
	public StudentOrder createOrder(StudentOrder stuOrder) throws Exception {

		JSONObject orderReq=new JSONObject();
		
		orderReq.put("amount", stuOrder.getAmount()*100);
		orderReq.put("currency", "INR");
		orderReq.put("receipt", stuOrder.getEmail());
		
		this.client=new RazorpayClient(razorPayKey, razorPaySecret);
		
		Order razorPayOrder=client.orders.create(orderReq);
		
		stuOrder.setRazorpayOrderId(razorPayOrder.get("id"));
		stuOrder.setOrderStatus(razorPayOrder.get("status"));
		
		repo.save(stuOrder);
		return stuOrder;
	}

	@Override
	public StudentOrder updateOrder(Map<String, String> responsePayLoad) {

		String razorPayOrderId=responsePayLoad.get("razorpay_order_id");
			StudentOrder order=repo.findByRazorpayOrderId(razorPayOrderId);
			order.setOrderStatus("PAYMENT_COMPLETED");
			StudentOrder updatedOrder=repo.save(order);
			return updatedOrder;
	}
	
	
	
	
	
}
