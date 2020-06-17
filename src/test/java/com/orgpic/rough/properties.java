package com.orgpic.rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class properties {

	/*public static void main(String[] args) throws IOException {
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir+"\\properties\\Config.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties config = new Properties();
		config.load(fis);
		System.out.println(config.getProperty("url"));
	
	}*/
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chandan\\eclipse-workspace\\com.orgpic\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.orgpick.com/pages/enter-mobile");
		Actions ac = new Actions(driver);
		WebElement mobile= driver.findElement(By.xpath("//input[@id='CustomerFormAllergies']"));
		mobile.sendKeys("123456");
		WebElement otp = driver.findElement(By.xpath("//input[@id='getopt']"));
		ac.click(otp).perform();
	}
	
	

}
