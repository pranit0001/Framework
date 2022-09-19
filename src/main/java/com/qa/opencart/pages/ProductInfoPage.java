package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;

	// 1. By locators
	By productHeader = By.cssSelector("div#content h1");
	By productImage = By.cssSelector("ul.thumbnails img");
	By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]//li");
	By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]//li");
	// 2. constructor

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue(/* String mainProductName */) {
		// By mainProduct = By.xpath("//h2[@text()='"+mainProductName+"']");
		String productHeaderVal = eleUtil.doElementGetText(productHeader); // its now not like depend on the parameter
		// passing in this method
		System.out.println("Product Header name:" + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImageCount() {
		int imageCount = eleUtil.waitForElementsToBeVisible(productImage, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
		System.out.println(imageCount);
		return imageCount;
	}

	public Map<String, String> getProductInfo() {
		productMap = new LinkedHashMap<String, String>();
		//HashMap is unordered
		//LinkedHashMap is ordered
		//TreeMap is  used for sorting purpose becaue it maintain sorted orderd 	
		//Add product name also in the map
		productMap.put("productName", getProductHeaderValue());	

		getProductMetaData();
		getProductPriceData();
		return productMap;

	}

	public void getProductMetaData() {

		// Brand: Apple
		// Product Code: Product 18
		// Reward Points: 800
		// Availability: In Stock
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText(); // getting the text of the respective WebElement
			String meta[] = text.split(":"); // method return static array , splitting on the basis of the :
			// -After Spitting ex Brand: Apple , this Brand: will store in the 0th index ie.
			// meta[0]
			// -and apple will stored in the meta[1] ie. 1st index , this is same for the
			// another WebElements also
			String key = meta[0].trim(); // will give the element at meta[0] with trimmed .
			String value = meta[1].trim();
			productMap.put(key, value);

		}
	}

	public void getProductPriceData() {
		//			$2,000.00
		//			Ex Tax: $2,000.00
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData); //storing the values in the List
		String productPrice = metaPriceList.get(0).getText().trim(); // storing the first element text
		String productExTaxPrice = metaPriceList.get(1).getText().trim(); // storing second element text
		productMap.put("productPrice", productPrice); // putting in the HashMap with hardcoaded key
		productMap.put("exTaxPrice", productExTaxPrice); //Putting in the hashMap with hardcoaded key


	}
}

