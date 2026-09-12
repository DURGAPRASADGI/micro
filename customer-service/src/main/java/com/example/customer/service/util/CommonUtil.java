package com.example.customer.service.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.customer.service.model.OrderStatus;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class CommonUtil {
	private MessageSource messageSource;
	
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
	
	public  void fieldValidation(String message,List<String> errors) {
		if(!StringUtils.hasText(message)) {
			errors.add(messageSource.getMessage("customer.email.blank", null, Locale.getDefault()));
		}
		
	}
	
	
	public void fieldValidationForLong(Long value, List<String> errors) {

	    if (value == null) {
	        errors.add(
	            messageSource.getMessage(
	                "customer.phone.null",
	                null,
	                Locale.getDefault()
	            )
	        );
	    } 
	    else if (String.valueOf(value).length() != 10) {
	        errors.add(
	            messageSource.getMessage(
	                "customer.phone.min",
	                null,
	                Locale.getDefault()
	            )
	        );
	    }
	}
	
}
