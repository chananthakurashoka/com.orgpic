package com.orgpic.testCases;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orgpic.base.TestBase;

public class TestCases extends TestBase {

	public WebElement account;
	public WebElement email;
	public WebElement password;
	public WebElement signIn;
	public WebElement searchFiled;
	public WebElement searchButton;
	public static WebElement signup;
	public static WebElement mobile;
	public static WebElement getOtp;
	public static WebElement getOtpErrorMessage;
	public static WebElement getOtpErrorMessageDisplay;
	public static List<WebElement> searchResult;
	public static WebElement cart;
	public static WebElement currentCartValue;
	public static WebElement getcurrentCartValue;
	public static WebElement pickWait;
	public static WebElement pick;
	public TestCases lpt;

	@Test(priority = 0)
	public void linkOnThepage() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		{
			int totalLink = links.size();
			System.out.println("Total Links on the page are " + totalLink);
			for (WebElement link : links) {
				System.out.println(link.getText());
			}
		}
	}

	@Test(priority = 1)
	public void clickOnAccount() throws Exception {

		account = driver.findElement(getLocatorsValue.getLocator("Account_field"));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(account));
		} catch (Exception e) {

			e.printStackTrace();
		}
		account.click();
	}

	@Test(priority = 2)
	public void clickSignupButton() throws Exception {
		signup = driver.findElement(getLocatorsValue.getLocator("Signup_field"));
		signup.click();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getDataValue.getData("implicit.wait")),
				TimeUnit.SECONDS);
		String signupPageTitle = driver.getTitle();
		System.out.println(signupPageTitle);

		mobile = driver.findElement(getLocatorsValue.getLocator("Mobile_field"));

		try {
			wait.until(ExpectedConditions.elementToBeClickable(mobile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mobile.sendKeys(getDataValue.getData("mobile"));

		getOtp = driver.findElement(getLocatorsValue.getLocator("Otp_Field"));
		ac.click(getOtp).perform();

		getOtpErrorMessageDisplay = driver.findElement(getLocatorsValue.getLocator("OtpError_FieldDisplay"));
		String attribute = getOtpErrorMessageDisplay.getAttribute("style");
		wait.until(ExpectedConditions.attributeContains(getOtpErrorMessageDisplay, "style", "display: none"));

		getOtpErrorMessage = driver.findElement(getLocatorsValue.getLocator("OtpError_Field"));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated((getLocatorsValue.getLocator("OtpError_Field"))));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String errorMessage = getOtpErrorMessage.getText();
		System.out.println(errorMessage);
		String requiredErrorMessage = "Invalid Number. Please Enter Correct Number.";
		Thread.sleep(2000);
		//Assert.assertEquals(errorMessage, requiredErrorMessage);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(errorMessage, requiredErrorMessage);
		sa.assertAll();
		driver.switchTo().defaultContent();
		Set<String> wid = driver.getWindowHandles();
		for (String handle : wid) {
			driver.switchTo().window(handle);
		}

		account = driver.findElement(getLocatorsValue.getLocator("Account_field"));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(account));
		} catch (Exception e) {

			e.printStackTrace();
		}
		account.click();
	}

	@Test(priority = 3)
	public void signIn() throws Exception {

		email = driver.findElement(getLocatorsValue.getLocator("EmailAddress_field"));
		email.sendKeys(getDataValue.getData("email"));
		password = driver.findElement(getLocatorsValue.getLocator("Password_field"));
		password.sendKeys(getDataValue.getData("password"));
		signIn = driver.findElement(getLocatorsValue.getLocator("Sign_button"));
		ac.click(signIn).perform();
	}

	@Test(priority = 4)
	public void search() throws Exception {
		searchFiled = driver.findElement(getLocatorsValue.getLocator("Search_filed"));
		searchFiled.sendKeys(getDataValue.getData("search"));
		searchButton = driver.findElement(getLocatorsValue.getLocator("Search_button"));
		ac.click(searchButton).perform();
	}

	@Test(priority = 5)
	public void selectProduct() throws Exception {
	//product_field:xpath://ul//a[contains(text(),'Organic Triphala')]
		searchResult = driver.findElements(getLocatorsValue.getLocator("product_field"));
		for(WebElement name : searchResult)
		{
			String productName = name.getText();
					System.out.println("Product purchasing :"+productName);
					if(productName.contains(getDataValue.getData("productRequired")))
			{
				name.click();
				break;
			}									
		}
		//Get the total count of added in cart.
		currentCartValue = driver.findElement(getLocatorsValue.getLocator("cartValue_field"));
		System.out.println("My cart value is :- "+currentCartValue.getText());
		
		//add product to cart
		pick=driver.findElement(getLocatorsValue.getLocator("pick_field"));
		ac.click(pick).perform();

		pickWait=driver.findElement(getLocatorsValue.getLocator("pickWait_field"));
		
		wait.until(ExpectedConditions.attributeContains(pickWait, "style", "height"));
		
		//Get the total count of added in cart after picking the product.
		getcurrentCartValue = driver.findElement(getLocatorsValue.getLocator("cartValue_field"));
		wait.until(ExpectedConditions.elementToBeClickable(getcurrentCartValue));
		System.out.println("My cart count after picking the product is :- "+getcurrentCartValue.getText());
		
		cart = driver.findElement(getLocatorsValue.getLocator("cart_field"));
		wait.until(ExpectedConditions.elementToBeClickable(cart));
		ac.pause(2000).click(cart).build().perform();
		
		//cart.click();
	}

	
}
