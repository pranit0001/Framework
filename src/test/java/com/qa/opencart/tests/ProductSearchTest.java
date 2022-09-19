package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductSearchTest extends BaseTest {

	@BeforeClass // precondition specific for this class only
	public void productSearchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	/*
	 * @Test(dataProvider="getProductData") public void productSearchTest() {
	 * searchResPage = accPage.doSearch("Macbook"); // giving searchResultsPage
	 * object hence reference will be save to // the BaseTest class productInfoPage
	 * = searchResPage.selectProduct("MacBook Pro"); // giving ProductInfoPage
	 * object hence reference // will be saved to the BaseTest String
	 * actProductHeaderName = productInfoPage.getProductHeaderValue();
	 * Assert.assertEquals(actProductHeaderName, "MacBook Pro"); }
	 */
	// updated with the dataProvide

	/* @DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook Air" }, { "macbook", "MacBook Pro" }, };

	} */
	
	@DataProvider
	public Object[][] getProductExcellData(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}

	@Test(dataProvider = "getProductExcellData")
	public void productSearchTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}

	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "macbook", "MacBook Air", 4 },
			{ "samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "getProductInfoData")
	public void getProductImageCountTest(String searchkey, String productName, int imageCount) {
		searchResPage = accPage.doSearch(searchkey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImageCount();
		Assert.assertEquals(actImageCount, imageCount);
	}

	// when one Test has multiple assertions then use SoftAssertion
	@Test 
	public void productDataTest() {// productInfoPageTest
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();

		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("exTaxPrice"), "Ex Tax: $2,000.00");
		softAssert.assertAll();
	}

}
