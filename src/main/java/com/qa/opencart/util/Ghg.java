package com.qa.opencart.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ghg {
public static void main(String []args )
{
	WebDriver driver= new ChromeDriver();
	WebDriverManager.chromedriver().setup();
}
}
