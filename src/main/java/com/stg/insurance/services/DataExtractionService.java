package com.stg.insurance.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.stg.insurance.models.genericdata.Document;

@Service
public interface DataExtractionService {

	Document readCategoryFromFile(String filepath, String recordDelimiter, String fieldDelimiter, String fieldDemarcator) throws IOException;
}
