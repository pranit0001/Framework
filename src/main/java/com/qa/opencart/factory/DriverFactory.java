package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class DriverFactory {
	public WebDriver driver;
	public OptionsManager optionsManager;
	public Properties prop;
	FileInputStream ip;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the driver on the basis of the provided
	 * browser
	 * 
	 * @param prop
	 * @return this return driver and now Returning ThreaLocal driver ie.tlDriver
	 */
	public WebDriver initBrowser(Properties prop) // This prop is coming from the BaseTest.java we are passing prop from
	// there

	{
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser name is :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please enter the proper Browsername : " + browserName);
		}
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		// this get method will return the copy of the thread Local driver.
	}

	/**
	 * this return properties reference with all the config properties
	 * 
	 * @return this return properties reference
	 */
	/*
		public Properties initProp() {
		Properties prop = new Properties(); // Properties is the class of java
		try {
			FileInputStream ip = new FileInputStream("./config/config.properties"); // making connection with the
			// properties file
			prop.load(ip); // load all the data of the provided properties file to the prop object of the
			// properties class
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop; // returning all the data of this properties file with the help of the prop
		// object
	} */
	 

 public Properties initProp()
	{
		prop = new Properties();

		//command line argument for the maven
		// mvn clean install -Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("Running on the Environment="+envName);

		if(envName == null) {
			System.out.println("Running on the qa Environment as no environment is given");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				switch(envName) {
				case "qa" :
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev" :
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case"prod" :
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				case"uat" :
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				default :
					System.out.println("please pass the right environment.");
				}
			} catch(FileNotFoundException e) {
				e.printStackTrace();	
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

}
 public static String getScreenshot() {
    File srcFile =  ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
      String path ="./screenshot/"+System.currentTimeMillis()+".png";
      //As we can not copy file object to the object of the String ie. path hence we have to pass the path to the 
      //constructor of the File class given below
      File destination = new File(path);
      try {
		FileUtils.copyFile(srcFile,destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
      return path;
	 
 }
 
 
 
}

