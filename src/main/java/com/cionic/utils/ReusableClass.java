package com.cionic.utils;


import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.cionic.constants.Constants;

public class ReusableClass {
	public static DateTimeFormatter formatter, timeformatter;

	public static String getTimeFormat() {
		formatter = DateTimeFormatter.ofPattern("hh:mm");
		timeformatter = DateTimeFormatter.ofPattern("a");
		LocalTime time = LocalTime.now();
		String formattedTime = time.format(formatter);
		String formattedHour = time.format(timeformatter).toUpperCase();
		String customFormattedTime = formattedTime + formattedHour;
		return customFormattedTime;
	}

	public static void waitTilElementPresence(Browser browser, String locatorType, String locator) {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), Duration.ofSeconds(Constants.MINIMUM_TIMEOUT));

		if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		} else if (locatorType.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
		}
	}
	
	public static void waitTilElementClickable(Browser browser, String locatorType, String locator) {
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), Duration.ofSeconds(Constants.MINIMUM_TIMEOUT));

		if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		} else if (locatorType.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
		}
	}
	
	public static void getWindowHandle(Browser browser, int windowIndex) {
		ArrayList<String> tabs = new ArrayList<>(browser.getDriver().getWindowHandles());
		browser.getDriver().switchTo().window(tabs.get(windowIndex));
	}
}
