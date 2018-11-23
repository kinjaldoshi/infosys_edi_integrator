package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoBController {
	
	@GetMapping ("/editPlatform/getAllLoBs")
	public List<String> getAllLoBs(){
		List<String> lineOfBusinessList = new ArrayList<>();
		lineOfBusinessList.add("Insurance");
		return lineOfBusinessList;
	}

}
