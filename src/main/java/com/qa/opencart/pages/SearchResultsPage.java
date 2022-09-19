package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.By locators
	By productCount = By.cssSelector("div.product-thumb");

	// 2.Constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. pageAction
	public int getSearchProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String searchProduct) {//This is method is used to check on which product we want to click
		By product = By.linkText(searchProduct); // can be called as dynamic locator
		eleUtil.doClick(product);
		return new ProductInfoPage(driver); // as clicking on the product will redirect us to that page hence, giving object of this method.

	}

}
