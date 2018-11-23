package com.stg.insurance.controllers;
/**
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.insurance.services.TemplateCustomizationServices;

/**
 * @author abhinavkumar.gupta
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ediPlatform")
public class TemplateCustomizationController {

	@Autowired
	TemplateCustomizationServices insuranceServices;

	@PostMapping(value="/getFilesName")
	public List<String> getFileNameFromS3ForGivenPath(@RequestHeader("File-type") String format) {

		List<String> fileNames = new ArrayList<>();
		try {
			fileNames = insuranceServices.getFileName(format);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileNames;

	}
	
	@PostMapping(value="/customizedTemplate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void customizedTemplate(@RequestBody String customTemplate) {
		
		if (customTemplate != null && !customTemplate.isEmpty()) {
			try {
				JSONObject jsonObject = new JSONObject(customTemplate);
				insuranceServices.uploadCustomTemplateToS3(jsonObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
