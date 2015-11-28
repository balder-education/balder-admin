/**
 * 
 */
package br.edu.unitri.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author marcos.fernando
 *
 */
public final class RegexUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String regexLetras = "^[a-zA-Z\\s]+$";
	private static final String regexNumeros = "^[0-9]";
	
	public static boolean isValidCampoString(String campo) {

		if (campo.trim().isEmpty()) { return true; }
		
		else {
			Pattern pattern = Pattern.compile(regexLetras);  
	        Matcher match = pattern.matcher(campo);  
	          
	        if(match.find()) {   
	        	return true;
	        } else {  
	            return false;            
	        }  
		}
	}
	
	public static boolean isValidCampoNumber(String campo) {

		if (campo.trim().isEmpty()) { return true; }
		
		else {
			Pattern pattern = Pattern.compile(regexNumeros);  
	        Matcher match = pattern.matcher(campo);  
	          
	        if(match.find()) {   
	        	return true;
	        } else {  
	            return false;            
	        }  
		}
	}
	
	public static boolean isValidCampoByRegex(String campo, String formato) {

		if (campo.trim().isEmpty()) { return true; }
		
		else {
			Pattern pattern = Pattern.compile(formato);  
	        Matcher match = pattern.matcher(campo);  
	          
	        if(match.find()) {   
	        	return true;
	        } else {  
	            return false;            
	        }  
		}
	}
	

}
