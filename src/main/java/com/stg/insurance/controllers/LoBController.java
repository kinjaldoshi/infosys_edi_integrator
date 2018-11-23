package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoBController {
	
	@GetMapping ("/ediPlatform/getAllLoBs")
	public List<String> getAllLoBs(){
		List<String> lineOfBusinessList = new ArrayList<>();
		lineOfBusinessList.add("Vehicle Insurance");
		lineOfBusinessList.add("Health Insurance");
		return lineOfBusinessList;
	}
}
