package com.stg.insurance.controllers;
/**
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.insurance.services.TemplateCustomisationServices;

/**
 * @author abhinavkumar.gupta
 *
 */
@RestController
@RequestMapping("/ediPlatform")
public class TemplateCustomisationController {

	@Autowired
	TemplateCustomisationServices insuranceServices;

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

}
