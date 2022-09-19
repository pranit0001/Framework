package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]//input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]//input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// INITIALIZIG DRIVER IN THE COSTRUCTOR
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page action

	public boolean userRegistration(String firstName, String lastName, String email, String phone, String password,
			String subscribe) {
		eleUtil.doSendKeysWithWait(this.firstName, AppConstants.MEDIUM_DEFAULT_TIME_OUT, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, phone);
		eleUtil.doSendKeys(this.password,password);
		eleUtil.doSendKeys(confirmPassword,password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		String actSuccessMesg = eleUtil.waitForElementVisible(sucessMessg, AppConstants.MEDIUM_DEFAULT_TIME_OUT)
				.getText();
		System.out.println("usrt registration success Mesg-----"+actSuccessMesg);

		if (actSuccessMesg.contains(AppConstants.REGISTER_SUCCESS_MESG)) {
			return true;
		}
		return false;
	}

	public void goToRegisterPage() { // this method defined in the RegisterPage class
		eleUtil.doClickWithWait(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		eleUtil.doClick(registerLink);
	}
	
}
