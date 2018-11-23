package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TemplateController {
	
	
	
	@GetMapping ("/ediPlatform/getAllTemplates")
	public List<String> getAllAgencies(@RequestParam ("agencyName") String agencyName){
		List<String> templates = new ArrayList<>();
		if (agencyName != null && !agencyName.isEmpty()) {
			if (agencyName.equals("Acord")) {
				templates.add("AL3");
				templates.add("X12");
			}else {
				templates.add("X12");
			}
		}
		
		return templates;
	}

}
