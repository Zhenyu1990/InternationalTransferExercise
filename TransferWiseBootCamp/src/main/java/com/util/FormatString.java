package com.util;

public class FormatString {

	public static String format(String oldString) {
		String newString = oldString.replace(".", "%2E");
		newString = newString.replace(" ", "%20");
		return newString;
	}
	
}
