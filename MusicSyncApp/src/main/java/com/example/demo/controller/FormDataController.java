package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.FormData;
import com.example.demo.services.FormDataService;

@Controller
public class FormDataController {

	@Autowired
	FormDataService fserv;
	
	@PostMapping("/submitForm")
	public String submitForm(@ModelAttribute FormData data, Model model) {
	    // Save the form data to the database using the service
	    fserv.saveFormData(data);

	    // Add a success message or result to the model
	    String resultMessage = "Form submitted successfully!";
	    model.addAttribute("resultMessage", resultMessage);

	    // Return the view name to be rendered
	    return "contactme";
	}
}
