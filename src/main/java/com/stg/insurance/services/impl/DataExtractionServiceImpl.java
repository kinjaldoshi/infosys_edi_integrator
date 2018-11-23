package com.stg.insurance.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.stg.insurance.models.genericdata.Category;
import com.stg.insurance.models.genericdata.Document;
import com.stg.insurance.models.genericdata.FieldValue;
import com.stg.insurance.models.genericdata.Record;
import com.stg.insurance.services.DataExtractionService;

public class DataExtractionServiceImpl implements DataExtractionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataExtractionServiceImpl.class);

	@Override
	public Document readCategoryFromFile(String filepath, String recordDelimiter, String fieldDelimiter, String fieldDemarcator)
			throws IOException {
		// TODO Read a category data from a file
		Category category = null;
		Document document = null;
		String content = new String(Files.readAllBytes(Paths.get(filepath)));
		if (StringUtils.isEmpty(content)) {
			// TODO Throw a service exception
		} else {
			// TODO Validate file for format
			document = new Document();
			category = new Category();
			document.setCategory(category);
			category.setSubCategories(new ArrayList<>());
			String[] records = content.split(recordDelimiter);
			category.setId(records[0].substring(0, 4));
			category.setRecords(new ArrayList<>(records.length));
			for (String recordString : records) {
				Record record = new Record();
				record.setFields(new HashMap<>());
				for (String fieldString : recordString.split(fieldDelimiter)) {
					String[] fieldComponents = fieldString.split(fieldDemarcator);
					record.getFields().put(fieldComponents[0], new FieldValue(fieldComponents[2], fieldComponents[1]));
				}
				category.getRecords().add(record);
			}
		}
		LOGGER.info("File contents read");
		return document;
	}

}
