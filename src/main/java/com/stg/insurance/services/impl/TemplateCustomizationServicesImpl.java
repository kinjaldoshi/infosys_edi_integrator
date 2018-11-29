package com.stg.insurance.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.stg.insurance.properties.S3Properties;
import com.stg.insurance.services.TemplateCustomizationServices;

/**
 * @author abhinavkumar.gupta
 *
 */
@Service
public class TemplateCustomizationServicesImpl implements TemplateCustomizationServices {

	@Autowired
	private AmazonS3 s3client;

	@Autowired
	private S3Properties s3Properties;

	private final static Logger LOGGER = LoggerFactory.getLogger(TemplateCustomizationServicesImpl.class);

	@Override
	public List<String> getFileName(String format) throws FileNotFoundException {

		List<String> files = getFileNamesFromS3(s3Properties.getPrefix() + "/" + format);
		if (files != null && !files.isEmpty()) {
			return files;
		}
		else {
			throw new FileNotFoundException();
		}

	}

	private List<String> getFileNamesFromS3(String prefix) {
		List<String> fileNamesWithPath = new ArrayList<String>();
		try {
			LOGGER.info("bucket name " + s3Properties.getBucketName());

			/* Request for all file paths in the sub path log-stgf-api/dflt */
			ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(s3Properties.getBucketName())
					.withPrefix(prefix);

			/* Request list of sub paths with absolute path detailed */
			ListObjectsV2Result result = s3client.listObjectsV2(request);

			/* Get the list of ObjectSummary from ListObjectsV2Result */
			for (S3ObjectSummary object : result.getObjectSummaries()) {
				/* Get a list of all the files that are encrypted */
				if (object.getKey().endsWith("json")) {
					fileNamesWithPath.add(object.getKey());
				}
			}
			LOGGER.info("===================== Listing Objects - Done! =====================");

		} catch (AmazonServiceException ase) {
			LOGGER.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
			LOGGER.info("Error Message:    " + ase.getMessage());
			LOGGER.info("HTTP Status Code: " + ase.getStatusCode());
			LOGGER.info("AWS Error Code:   " + ase.getErrorCode());
			LOGGER.info("Error Type:       " + ase.getErrorType());
			LOGGER.info("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			LOGGER.info("Caught an AmazonClientException: ");
			LOGGER.info("Error Message: " + ace.getMessage());
		}

		return fileNamesWithPath;
	}

	@Override
	public void uploadCustomTemplateToS3(JSONObject customTemplate) throws Exception {

		if (customTemplate != null) {
			System.out.println("JSON: " + customTemplate.toString());
			if (customTemplate.has("templateName")) {
				try {
					Path path = Files.createTempFile(customTemplate.getString("templateName"),  ".json");
					System.out.println("File Path = " + path.toString());
					File file  = path.toFile();
					FileUtils.writeByteArrayToFile(file, customTemplate.toString().getBytes());
					String s3Path = s3Properties.getBucketName() + s3Properties.getPrefix();
					System.out.println("S3 Path = " + s3Path);
					s3client.putObject(s3Properties.getBucketName(), s3Properties.getAl3Prefix() + customTemplate.getString("templateName") + ".json", 
							new File(path.toString()));

				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				throw new Exception("Custom Template received from Template Customizer does not contain template name");
			}
		}else {
			throw new Exception("Custom Template received from Template Customizer is null");

		}
	}

	@Override
	public String getTemplateByNameFromS3(String lineOfBusiness, String agencyName, String templateType, String templateName) throws Exception {

		String template = null;

		if (lineOfBusiness != null &&agencyName != null && templateType != null && templateName != null  &&
				!lineOfBusiness.isEmpty() && !agencyName.isEmpty()  && !templateType.isEmpty() && !templateName.isEmpty()) {

			StringBuffer path = new StringBuffer("insurance/");
			path.append(agencyName).append("/").append(templateType).append("/").append(templateName);

			System.out.println("Path: " + path.toString());

			template = s3client.getObjectAsString(s3Properties.getBucketName(), path.toString());

			//System.out.println("Template = " + templateBody);

		} else {
			throw new Exception("Invalid Input");
		}

		return template;
	}

	
	public String getTemplateNameAndDescriptionFromS3() {
		JSONArray jsonArray = new JSONArray();

		try {
			ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(s3Properties.getBucketName())
					.withPrefix(s3Properties.getPrefix());

			/* Request list of sub paths with absolute path detailed */
			ListObjectsV2Result result = s3client.listObjectsV2(request);

			/* Get the list of ObjectSummary from ListObjectsV2Result */
			for (S3ObjectSummary object : result.getObjectSummaries()) {
				
				JSONObject jsonObject = new JSONObject();
				
				/* Get a list of all the files that are encrypted */
				if (object.getKey().endsWith("json")) {
					String templateName = object.getKey().substring(object.getKey().lastIndexOf("/") + 1);
					System.out.println(templateName);
					String jsonTemplate = s3client.getObjectAsString(s3Properties.getBucketName(), object.getKey());
					try {
						JSONObject jsonTemplateObject = new JSONObject(jsonTemplate);
						String description = jsonTemplateObject.getString("description");
						System.out.println(description);
						jsonObject.put("templateName", templateName);
						jsonObject.put("description", description);
						jsonArray.put(jsonObject);
						System.out.println(jsonObject.toString());
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			LOGGER.info("===================== Listing Objects - Done! =====================");

		} catch (AmazonServiceException ase) {
			LOGGER.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
			LOGGER.info("Error Message:    " + ase.getMessage());
			LOGGER.info("HTTP Status Code: " + ase.getStatusCode());
			LOGGER.info("AWS Error Code:   " + ase.getErrorCode());
			LOGGER.info("Error Type:       " + ase.getErrorType());
			LOGGER.info("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			LOGGER.info("Caught an AmazonClientException: ");
			LOGGER.info("Error Message: " + ace.getMessage());
		}
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}

}
