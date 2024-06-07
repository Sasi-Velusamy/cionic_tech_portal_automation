package com.cionic.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyParser {

	public static Properties properties;

	/**
	 * 
	 * This method is used to get locator key value.
	 * 
	 * @param filePath
	 * @param KeyValue
	 * @return value
	 */
	public static String getLocatorValue(String filePath, String keyValue) {
		properties = new Properties();
		String value;

		try {
			properties.load(new FileInputStream(filePath));
		} catch (IOException ioException) {

		}
		value = properties.getProperty(keyValue, "");
		return value;
	}
}
