package com.orgpic.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocatorsMain extends TestBase {

	Properties prop;
	public WebElement account;

	public LocatorsMain(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getData(String str) throws Exception {
		String data = prop.getProperty(str);
		return data;
	}

	public By getLocator(String str) throws Exception {
		String locator = prop.getProperty(str); // e.g locator="name:username"
		// now we are splitting the value in to locator type and locator value

		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		} else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("classname"))) {
			return By.className(locatorValue);
		} else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))) {
			return By.tagName(locatorValue);
		} else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))) {
			return By.linkText(locatorValue);
		} else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))) {
			return By.cssSelector(locatorValue);
		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);
		} else
			throw new Exception("Locator Type " + locatorType + " not defined");

	}
	public void clickOnAccount() throws Exception {

		account = driver.findElement(getLocatorsValue.getLocator("Account_field"));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(account));
		} catch (Exception e) {

			e.printStackTrace();
		}
		account.click();
	}

}
