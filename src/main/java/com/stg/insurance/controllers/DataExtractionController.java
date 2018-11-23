package com.stg.insurance.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.insurance.constants.HeaderConstants;
import com.stg.insurance.models.genericdata.Document;
import com.stg.insurance.services.DataExtractionService;
import com.stg.insurance.utils.DelimiterUtilities;

@RestController
@RequestMapping("/v1")
public class DataExtractionController {

	@Autowired
	private DataExtractionService dataExtractionService;

	@GetMapping("/extract/file")
	public ResponseEntity<Document> readContentsFromFile(@RequestHeader(name = HeaderConstants.FILE_NAME) String filepath,
			@RequestHeader(name = HeaderConstants.RECORD_DELIMITER) String recordDelimiter,
			@RequestHeader(name = HeaderConstants.FIELD_DELIMITER) String fieldDelimiter,
			@RequestHeader(name = HeaderConstants.FIELD_DEMARCATOR) String fieldDemarcator) throws IOException {
		return new ResponseEntity<>(
				dataExtractionService.readCategoryFromFile(filepath, DelimiterUtilities.getEscapeSequence(recordDelimiter), fieldDelimiter, fieldDemarcator), HttpStatus.OK);
	}

}
