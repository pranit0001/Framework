	package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterTest extends BaseTest {
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage(); // this method is from login LoginPage class ,and object is define in the BaseTest class
	}

//	@DataProvider
//	public Object[][] getRegData() {
//		return new Object[][] {
//			{ "pranitone", "automationone", "pranitone@gmail.com", "9090909010", "Pranit@111", "yes" },
//			{ "pranittwo", "automationtwo", "pranittwo@gmail.com", "9090909020", "Pranit@222", "yes" },
//			{ "pranitthree", "automationthree", "pranitthree@gmail.com", "9090909030", "Pranit@333", "yes" }, };
//	}
	
	public String randomEmail() {
		Random random = new Random();
		String email = "automation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegExcelData(){
	  Object regData[][]= ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	  return regData;
	  }
	@Test(dataProvider = "getRegExcelData")
	public void getRegTest(String firstName, String lastName, String phone, String password,
			String subscribe) //remove email from here as we deleted the email id column in the sheet
	{
		System.out.println(randomEmail());
		boolean successFlag = regPage.userRegistration(firstName, lastName, randomEmail(), phone, password, subscribe);
		regPage.goToRegisterPage(); // this method is from RegisterPage class
		Assert.assertEquals(successFlag, true);
	}
}//1.@BeforeTest will gives us the initializing the browser and getting to the url 
//2. then we are using goToRegisterPage in the LoginPage class which will go that register page gives us the RegisterPage object and   and then 
//3.with that we put the data and create account with the help of the getRegTest() method and then 
//4.with the goToRegisterPage method in the RegisterPage class  we will come to again register page by clicking on the logout and register link
