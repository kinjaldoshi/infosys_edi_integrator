package com.stg.insurance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.insurance.properties.InsuranceProperties;
import com.stg.insurance.services.S3CredentialsAccessService;

@RestController
@CrossOrigin
public class LoBController {
	
	@Autowired
	InsuranceProperties insuranceProperties;
	
	@Autowired 
	private S3CredentialsAccessService s3CredentialClient;
	
	@GetMapping ("/ediPlatform/getAllLoBs")
	public List<String> getAllLoBs(){
		System.out.println("LOBs = " + insuranceProperties.getLineOfBusinessList());
		return insuranceProperties.getLineOfBusinessList();
	}
}
