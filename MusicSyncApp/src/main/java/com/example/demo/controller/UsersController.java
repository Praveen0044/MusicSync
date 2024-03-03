package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService uServ;


	//	public String addUser(
	//			@RequestParam String username,
	//			@RequestParam String email ,
	//			@RequestParam String password,
	//			@RequestParam String gender,
	//			@RequestParam String role,
	//			@RequestParam String address)
	//		System.out.println(user.getUsername()+" " +user.getEmail()+" "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());

	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
	{
		boolean userstatus = uServ.emailExists(user.getEmail());
		if(userstatus == false) {
			uServ.addUser(user);
			return "registerSuccess";
		}
		else{
			return "register_failed";
		}
	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,Model model,HttpSession session) {
		if(uServ.validateUser(email, password) == true) {
			
			session.setAttribute("email", email);
			
			if(uServ.roles(email).equals("admin")) {
				return "AdminHome";
			}else {
				String username = uServ.usernameFind(email);
				model.addAttribute("username", username);
				return "customerHome";
			}
		}
		else {
			return "loginfail";
		}
	}
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		Users user = uServ.getUser(email);
		
		boolean userStatus = user.isPremium();
		if(userStatus == true) {
			return "PremiumUser";
		}else {
			return "makePayment";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login";
	}
	
}


