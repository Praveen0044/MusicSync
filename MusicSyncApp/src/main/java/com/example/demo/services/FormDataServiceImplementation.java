package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.FormData;
import com.example.demo.repositories.FormDataRepository;

@Service
public class FormDataServiceImplementation implements FormDataService{
	@Autowired
	FormDataRepository frepo;

	@Override
	public void saveFormData(FormData data) {

	        // Save the form data to the database
	        frepo.save(data);
	}

}
