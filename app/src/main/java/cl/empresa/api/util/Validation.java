package cl.empresa.api.util;

import java.util.regex.Pattern;

public class Validation {
	
	public static boolean isStringNullOrEmpty(String str) {
	    return str==null || "".equals(str.trim());
	}
	
	public static boolean isEmail(String email) {
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    return Pattern.compile(regexPattern)
	    		   .matcher(email)
	    		   .matches();
	}
}
