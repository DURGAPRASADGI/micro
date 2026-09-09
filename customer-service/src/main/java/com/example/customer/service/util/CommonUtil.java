package com.example.customer.service.util;


public class CommonUtil {
	
	private CommonUtil() {
		
	}
	

	
	public static Long getLong(Object obj) {
		if(obj instanceof Long value) {
			return value;
		}
		
		return null;
		
	}

	public static String getString(Object obj) {
		if(obj instanceof String value) {
			return value;
		}
		
		return null;
		
	}
	
	public static Integer getInt(Object obj) {
		if(obj instanceof Integer value) {
			return value;
		}
		
		return null;
		
	}
}
