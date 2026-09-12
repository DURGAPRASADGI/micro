package com.example.customer.service.util;

import java.math.BigDecimal;

import com.example.customer.service.model.OrderStatus;

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
	
	public static BigDecimal getBigDecimal(Object obj) {
		if(obj instanceof BigDecimal value) {
			return value;
		}
		
		return null;
		
	}
	
	public static OrderStatus getEnum(Object obj) {
		
		if(obj instanceof Number number) {
			int ord=number.intValue();
			OrderStatus[] orderStatus=OrderStatus.values();
			
			if(ord>=0 && ord<orderStatus.length) {
				return orderStatus[ord];
				
			}
		}
			
		return null;
		
	}
	
	
	
	public static <E extends Enum<E>> E getEnum(Object obj, Class<E> enumClass) {

	    if (enumClass.isInstance(obj)) {
	        return enumClass.cast(obj);
	    }

	    return null;
	}
	
	
}
