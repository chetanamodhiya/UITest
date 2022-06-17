package com.magento.reusableComponents;

import java.text.DecimalFormat;

/**
 * @author: Chetana
 */

public class MathUtility {
	//Below method is used to remove '$' and ','
	public static String regularExpressionRetainDot(String value) {
		return value.replaceAll("[$,]", "");
	 }
	
	//Below method converts String to double 
	public static double parseStringToDouble(String textInNumber) {
		return Double.parseDouble(textInNumber);
	}
	
	//Below method converts String to int 
		public static double parseStringToInteger(String textInNumber) {
			return Integer.parseInt(textInNumber);
		}
		
	//Below method converts String to int 
		public static String parseIntToString(int number) {
			return String.valueOf(number);
		}	
				
	
	//Below method converts double to String  
	public static String parseDoubleToString(double number) {
			return String.valueOf(number);
		}
		
	//Below method can be used to calculate the percentage increase
	public static Double percentageCount(Double value){
			Double percentage=value/100;
			return percentage;
		}	
	
	//Below method can be used to calculate the percentage increase
	public static Double percentageCalculator(Double oldValue, Double newValue){
		Double percentage=((newValue-oldValue)/oldValue)*100;
		return percentage;
	}
	
	//Below method can be used to retain only 2 decimal places 
	public static Double valueInDecimalFormat(Double value) {
		DecimalFormat dfHash = new DecimalFormat("#.##");
		return Double.parseDouble(dfHash.format(value));
	}
	
	//Below method can be used to remove negative sign 
	public static Double removeNegativeSign(Double value) {
		return Math.abs(value);
	}
	
}
