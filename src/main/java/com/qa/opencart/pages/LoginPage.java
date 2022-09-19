package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {

	// 1.By locators
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. LoginPage Constructor
	public LoginPage(WebDriver driver) // this driver is came from the BaseTest class
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3. Page actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT); // driver.getTitle();
		System.out.println("title of the login page :" + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println(url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("username:" + username);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.SMALL_DEFAULT_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver); // giving the accountPage constructor same session ID. and creating the object
											// of the AccountsPAge class
		// return eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE,
		// AppConstants.SMALL_DEFAULT_TIME_OUT);

		// driver.findElement(emailId).sendKeys(username);
		// driver.findElement(this.password).sendKeys(password);
		// driver.findElement(loginBtn).click();
		// return driver.getTitle();
		// to check after login will it be reached to the desired page or not hence
		// getting title of the homepage
		// Above is modified
	}

	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
		
	}

}
