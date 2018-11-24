/**
 * 
 */
package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kinjaldoshi
 *
 */
@RestController
@CrossOrigin
public class AgencyController {
	
	@GetMapping ("/ediPlatform/getAllAgencies")
	public List<String> getAllAgencies(@RequestParam ("lineOfBusiness") String lineOfBusiness){
		System.out.println("Line of Business: " + lineOfBusiness);
		List<String> agencies = new ArrayList<>();
		if (lineOfBusiness != null && !lineOfBusiness.isEmpty()) {
			if (lineOfBusiness.equals("Vehicle Insurance")) {
				agencies.add("Acord");
				agencies.add("ASC");
			}
			if (lineOfBusiness.equals("Health Insurance")) {
				agencies.add("Hipaa");
				agencies.add("ASC");
			}
		}
		return agencies;
	}


}
