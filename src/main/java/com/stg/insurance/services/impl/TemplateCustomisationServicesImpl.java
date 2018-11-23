package com.stg.insurance.services.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
import com.stg.insurance.properties.CommonProperties;
import com.stg.insurance.services.TemplateCustomisationServices;

/**
 * @author abhinavkumar.gupta
 *
 */
@Service
public class TemplateCustomisationServicesImpl implements TemplateCustomisationServices {

	@Autowired
	private AmazonS3 s3client;

	@Autowired
	private CommonProperties commonProperties;

	private final static Logger LOGGER = LoggerFactory.getLogger(TemplateCustomisationServicesImpl.class);

	@Override
	public List<String> getFileName(String format) throws FileNotFoundException {

		List<String> files = getFileNamesFromS3(commonProperties.getPrefix() + "/" + format);
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
			LOGGER.info("bucket name " + commonProperties.getBucketName());

			/* Request for all file paths in the sub path log-stgf-api/dflt */
			ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(commonProperties.getBucketName())
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

}
