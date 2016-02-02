package scapi.common.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {
	
	public static boolean isNullOrEmpty(String value){
		return value == null || value.isEmpty();
	}
	
	public static boolean isValueMatch(String value1, String value2){
		return value1.equalsIgnoreCase(value2); 
	}
	
	public static boolean isInvalidInteger(String value){
		return !StringUtils.isNumeric(value);
		
	}
	
	public static boolean isContainLetter(String value){
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(value);
		return m.find();
	}
}
