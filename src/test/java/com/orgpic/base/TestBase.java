package com.orgpic.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {

	public String workingDir;
	public LocatorsMain getDataValue;
	public LocatorsMain getLocatorsValue;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions ac;
	
	@BeforeSuite (alwaysRun=true)
	public void setUp() throws Exception {
		if (driver == null) {
			workingDir = System.getProperty("user.dir");
			getDataValue = new LocatorsMain(workingDir + "\\properties\\Config.properties");
			getLocatorsValue = new LocatorsMain(workingDir + "\\properties\\Locators.properties");

			// Setting webdriver.gecko.driver property
			System.setProperty("webdriver.gecko.driver", workingDir + "\\Drivers\\geckodriver.exe");

			// Setting the webdriver.ie.driver property to its executable's location
			System.setProperty("webdriver.ie.driver", workingDir + "\\Drivers\\IEDriverServer.exe");

			// Setting the webdriver.chrome.driver property to its executable's location
			System.setProperty("webdriver.chrome.driver", workingDir + "\\Drivers\\chromedriver.exe");

			if (getDataValue.getData("browser").equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			}
			if (getDataValue.getData("browser").equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			}
			if (getDataValue.getData("browser").equalsIgnoreCase("FireFox")) {
				driver = new FirefoxDriver();
			}

			driver.manage().deleteAllCookies();
			driver.get(getDataValue.getData("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(getDataValue.getData("implicit.wait")),
					TimeUnit.SECONDS);
			wait=new WebDriverWait(driver, 10);
			ac=new Actions(driver);
		}

	}
	
	@AfterSuite
	public void tearDown() throws Exception
	{
		if(driver!=null)
		{
			driver.quit();
		}
			
	}

}
