package com.cionic.testscript;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.cionic.constants.Constants;
import com.cionic.pages.LoginPage;
import com.cionic.testsuite.SampleTestSuiteBase;

public class TestInvalidEmailToken extends SampleTestSuiteBase {
	private ReportLogService report = new ReportLogServiceImpl(TestInvalidEmail.class);

	@Test
	@Parameters({ "os", "osVersion", "browser", "browserVersion" })
	public void invalidLoginEmail(String os, String osVersion, String br, String browserVersion) {
		report.info("Opening the gmail login page");
		browser.openURL(Constants.GMAIL_SIGNIN_URL, os, osVersion, br, browserVersion);
		browser.maximizeWindow();
		LoginPage emailToken = new LoginPage();
		emailToken.invalidEmailToken(browser);
	}
}
