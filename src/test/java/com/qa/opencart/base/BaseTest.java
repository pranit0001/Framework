package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginPage;
	public Properties prop;
	public AccountsPage accPage;
	public SearchResultsPage searchResPage;
    public ProductInfoPage productInfoPage;	
    public SoftAssert softAssert;
    public RegisterPage regPage;
	@BeforeTest //as it is before test hence , we can initialized in the setup method 
	public void setup() {
		df = new DriverFactory(); // initializing object of the DriverFactory class
		prop = df.initProp(); // initializing the object of the Properties class and giving all the data of
								// the config.properties file
		                       //this driver becomes the tlDrver.
		driver = df.initBrowser(prop); // passing the object as this object have all the data
		// as passing the prop , we can use the data in the initBrowser method so that
		// we can letter used same for the headless or incognito mode.
		// no need to use 'this'keyword here as we have not define the variable of the
		// setup method having name driver
		loginPage = new LoginPage(driver); // passing initialized driver so that driver can be further used in the
											// LoginPage class
		softAssert = new SoftAssert();
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
