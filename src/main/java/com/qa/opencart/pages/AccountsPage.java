package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchField = By.xpath("//input[@name='search']");
	private By logoutLink = By.linkText("Logout");
	private By accPageHeader = By.xpath("//div[@id='content']/h2");
	private By searchIcon = By.xpath("//span[@class = 'input-group-btn']");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("The title of the Account page is :" + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println(url);
		return url;
	}

	public List<String> getAccountSectionsHeaderList() {
		return eleUtil.getAllElementsTextList(accPageHeader, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}

	public boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	//common page action
	public SearchResultsPage doSearch(String productName) {
    eleUtil.doSendKeysWithWait(searchField,AppConstants.SMALL_DEFAULT_TIME_OUT, productName);
    eleUtil.doClick(searchIcon);
	return new SearchResultsPage(driver);
	}
	
	
	
	
	

}
