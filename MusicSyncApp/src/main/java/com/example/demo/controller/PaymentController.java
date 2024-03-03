package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	
	@Autowired
	@Qualifier("usersServiceImplementation")
	UsersService userv;

	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(Model model) {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_LcPEtTLTnDHQ1n", "OWwrMkFir3fcSlucOwoGO2zf");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

			 order = razorpay.orders.create(orderRequest);
			 model.addAttribute("orderId", order.get("id"));
			System.out.println(order.toString());
		} catch (Exception e) {
			System.out.println("exception while creating order");
		}
		return  order.toString();
	}
	

	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_LcPEtTLTnDHQ1n", "OWwrMkFir3fcSlucOwoGO2zf");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "OWwrMkFir3fcSlucOwoGO2zf");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//payment-success -> update to premium user
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Users user  = userv.getUser(email);
	     user.setPremium(true);
		userv.updateUser(user);
		
		return "login";
	}
	//payment-failure -> redirect to login
	@GetMapping("payment-failure")
	public String paymentFailure() {
		//return payment-error page  or 
		return "login";
	}
}
