package com.stg.insurance.utils;

import java.util.HashMap;

public class DelimiterUtilities {

	private static HashMap<String, String> delimiterMapper;
	
	static {
		delimiterMapper = new HashMap<>();
		delimiterMapper.put("CRLF", "\r\n");
		delimiterMapper.put("LF", "\n");
		delimiterMapper.put("CR", "\r");
	}
	
	public static String getEscapeSequence(String key) {
		return delimiterMapper.get(key);
	}
	
	private DelimiterUtilities() {
		super();
	}
}
