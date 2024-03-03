package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}

	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	@GetMapping("/map-about")
	public String about() {
		return "about";
	}
	@GetMapping("/map-contact")
	public String contact() {
		return "contactMe";
	}
	
	@GetMapping("/map-add")
	public String addSongMapping() {
		return "addsongs";
	}
	@GetMapping("/samplePyment")
	public String samplePyment() {
		return "samplePyment";
	}
}


