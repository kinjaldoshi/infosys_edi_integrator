/**
 * 
 */
package com.stg.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.insurance.properties.InsuranceProperties;

/**
 * @author kinjal.doshi
 *
 */
@RestController
@CrossOrigin
public class ReportingController {
	
	@Autowired
	InsuranceProperties insuranceProperties;
	
	@GetMapping ("/ediPlatform/getReportingGroups")
	public List<String> getReportingGroups (@RequestParam ("templateName") String templateName) {
		
		List<String> reportingGroups = new ArrayList<>();
		if  (templateName != null && !templateName.isEmpty()) {
			if (templateName.equalsIgnoreCase("AL3")) {
				reportingGroups = insuranceProperties.getAl3ReportingGroups();
			}
		}
		return reportingGroups;	
	}
}
