package com.cionic.constants;

import java.io.File;

public class FilePathConstants {
	public final static String USER_HOME = System.getProperty("user.dir") + File.separator;
	
	public final static String RESOURCES_HOME = USER_HOME + "src" + File.separator + "main" + File.separator
			+ "resources" + File.separator;

	public final static String LOCATOR_HOME = RESOURCES_HOME + "locators" + File.separator;
	
	//Locator File Path:
	public static final String LOGIN_PAGE_FILEPATH = LOCATOR_HOME + "login_page.properties";

	public static final String ADMIN_PORTAL_PAGE_FILEPATH = LOCATOR_HOME + "admin_portal_page.properties";
}
