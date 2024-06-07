package com.cionic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.cionic.constants.Constants;
import com.cionic.constants.FilePathConstants;
import com.cionic.pagekeys.AdminPortalPageKeys;
import com.cionic.pagekeys.LoginPageKeys;


import static com.cionic.utils.ReusableClass.*;
import static com.cionic.utils.PropertyParser.*;

public class LoginPage {
	private ReportLogService report = new ReportLogServiceImpl(LoginPage.class);
	
	public void loginLogoutScreen(Browser browser) {
		gmailLoginPage(browser);
		cionicLogin(browser);
		loginEmailVerification(browser);
		cionicAdminPortalLogout(browser);
	}
	
	public void invalidLoginEmail(Browser browser) {
		browser.getTextField().enterTextField(LocatorType.ID,getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_TEXT_FIELD),Constants.INVAILD_EMAIL_ID);
		browser.getClick().performClick(LocatorType.CLASSNAME, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_BUTTON));
		boolean emailTextFieldStatus = browser.getDriver().findElements(By.id(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_TEXT_FIELD))).size() > 0;
		Verify.verifyBoolean(emailTextFieldStatus, true, "Verify invalid email login");
	}
	
	public void invalidEmailToken(Browser browser) {
		gmailLoginPage(browser);
		cionicLogin(browser);
		loginEmailVerification(browser);
		invalidEmailTokenVerification(browser);
	}
	
	public void gmailLoginPage(Browser browser) {
		browser.getTextField().enterTextField(LocatorType.ID, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH,LoginPageKeys.EMAIL_TEXT_FIELD),Constants.CIONIC_EMAIL_ID);
		waitTilElementClickable(browser, "xpath", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_NEXT_BUTTON));
		browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_NEXT_BUTTON));
		waitTilElementPresence(browser,"name",getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_PASSWORD));
		browser.getDriver().findElement(By.name(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_PASSWORD))).sendKeys(Constants.CIONIC_PASSWORD + Keys.ENTER);
		waitTilElementPresence(browser, "xpath",getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.PRIVACY_ELEMENT_TEXT));
	}
	
	public void cionicLogin(Browser browser) {
		browser.getDriver().get(Constants.CIONIC_LOGIN_URL);
		browser.getTextField().enterTextField(LocatorType.ID,getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_TEXT_FIELD),Constants.TECH_EMAIL_ID);
		browser.getClick().performClick(LocatorType.CLASSNAME, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_BUTTON));
		boolean loginCompleteStatus = browser.getDriver().findElements(By.xpath(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_COMPLETED_TEXT))).size() > 0;
		Verify.verifyBoolean(loginCompleteStatus, true, "Verify the email submission");
	}
	
	public void loginEmailVerification(Browser browser) {
		browser.getDriver().get(Constants.GMAIL_URL);
		waitTilElementPresence(browser, "xpath", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_REFRESH_BUTTON));
		browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_REFRESH_BUTTON));
		waitTilElementPresence(browser, "xpath", gmailLocator());
		browser.getClick().performClick(LocatorType.XPATH, gmailLocator());
		waitTilElementClickable(browser, "xpath", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_HERE_LINK));
		browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_HERE_LINK));
		getWindowHandle(browser, 1);
	}
	
	public void cionicAdminPortalLogout(Browser browser) {
		browser.getDriver().get(Constants.CIONIC_ADMIN_URL);
		boolean lookUpStatus = browser.getDriver().findElements(By.xpath(getLocatorValue(FilePathConstants.ADMIN_PORTAL_PAGE_FILEPATH, AdminPortalPageKeys.LOOKUP_TEXT))).size() > 0;
		Verify.verifyBoolean(lookUpStatus, true, "Verify LookUp element visible");
		browser.getClick().performClick(LocatorType.ID, getLocatorValue(FilePathConstants.ADMIN_PORTAL_PAGE_FILEPATH, AdminPortalPageKeys.TECHNICIAN_DROPDOWN));
		browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.ADMIN_PORTAL_PAGE_FILEPATH, AdminPortalPageKeys.LOGOUT_BUTTON));
		waitTilElementPresence(browser, "id", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_HERE_TEXT));
		boolean loginHereStatus = browser.getDriver().findElements(By.id(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.LOGIN_HERE_TEXT))).size() > 0;
		Verify.verifyBoolean(loginHereStatus, true, "Verify login here element");
		boolean ExistingReseacherStatus = browser.getDriver().findElements(By.xpath(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EXISTING_RESEACHER_TEXT))).size() > 0;
		Verify.verifyBoolean(ExistingReseacherStatus, true, "Verify Existing researcher element");
	}
	
	// Note: Need to update the code for email verification:
	public void invalidEmailTokenVerification(Browser browser) {
		browser.getDriver().get(Constants.CIONIC_ADMIN_URL);
		browser.getDriver().close();
		getWindowHandle(browser, 0);
		boolean emailTokenStatus = browser.getDriver().findElements(By.xpath(getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.INVALID_EMAIL_TOKEN))).size() > 0;
		if(emailTokenStatus == true) {
			waitTilElementPresence(browser, "xpath", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.INVALID_EMAIL_TOKEN));
			browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.INVALID_EMAIL_TOKEN));
		}
		waitTilElementClickable(browser, "xpath", getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_HERE_LINK));
		browser.getClick().performClick(LocatorType.XPATH, getLocatorValue(FilePathConstants.LOGIN_PAGE_FILEPATH, LoginPageKeys.EMAIL_HERE_LINK));
		getWindowHandle(browser, 1);
		boolean lookUpStatus = browser.getDriver().findElements(By.xpath(getLocatorValue(FilePathConstants.ADMIN_PORTAL_PAGE_FILEPATH, AdminPortalPageKeys.LOOKUP_TEXT))).size() > 0;
		Verify.verifyBoolean(lookUpStatus, false, "Verify LookUp element visible");
	}
	
	
	public static String gmailLocator() {
		String gmailLoc = "//span[contains(text(),'"+getTimeFormat()+"')]";
		return gmailLoc;
	}
}
