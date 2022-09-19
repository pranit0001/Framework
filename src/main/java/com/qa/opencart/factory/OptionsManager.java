package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	// now we have to use config.properties files whoes code is setting up in the
	// DriverFactory class
	// then we dont have to create the unneccessary object and dont want to extend
	// then we can use help of constructor
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions(); // default class of the selenium
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			co.addArguments("--headless");//we do not need to write the curly bracces as there is only one statement in the if
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		return co;
	}// Boolean.parseBoolean is a wrapper class we are using to convert the string in to the the boolean
	//as this prop.getProperty will give true as a string not as a boolean
	//in the config.properties file which ever value of the headless or incognito is equal to true then this is going to execute

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions(); // default class of the selenium
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--incognito");
		return fo;
	}
	
	
	
	
	
}
