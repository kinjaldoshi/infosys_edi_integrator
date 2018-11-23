/**
 * 
 */
package com.stg.insurance.services;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author abhinavkumar.gupta
 *
 */
public interface TemplateCustomisationServices {

	List<String> getFileName(String format) throws FileNotFoundException;
	
}
