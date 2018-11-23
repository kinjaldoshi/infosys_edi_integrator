/**
 * 
 */
package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kinjaldoshi
 *
 */
public class AgencyController {
	
	@GetMapping ("/ediPlatform/getAllAgencies")
	public List<String> getAllAgencies(){
		List<String> agencies = new ArrayList<>();
		agencies.add("Accord");
		agencies.add("Hipaa");
		return agencies;
	}


}
