package com.cionic.testscript;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.cionic.pages.LoginPage;
import com.cionic.testsuite.SampleTestSuiteBase;

public class SampleTestScript extends SampleTestSuiteBase {

	private ReportLogService report = new ReportLogServiceImpl(SampleTestScript.class);

	@Test
	@Parameters({ "os", "osVersion", "browser", "browserVersion" })
	public void sampleTest(String os, String osVersion, String br, String browserVersion) {
		browser.openURL("https://accounts.google.com", os, osVersion, br, browserVersion);
		browser.maximizeWindow();
		LoginPage loginPage = new LoginPage();
		loginPage.loginLogoutScreen(browser);
	}
}
