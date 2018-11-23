package com.stg.insurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stg.insurance.services.DataExtractionService;
import com.stg.insurance.services.impl.DataExtractionServiceImpl;

@Configuration
public class Beans {
	
	@Bean
	public DataExtractionService dataExtractionServiceImpl() {
		return new DataExtractionServiceImpl();
	}
}
