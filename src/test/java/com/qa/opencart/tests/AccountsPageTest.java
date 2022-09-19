package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.print.attribute.SetOfIntegerSyntax;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void getAccountsPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void getAccountsPageUrlTest() {
		String actUrl = accPage.getAccountPageUrl();
		Assert.assertEquals(actUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION), true);
	}

	@Test
	public void getAccountSectionsHeaderListTest() {
		List<String> actSecHeaderList = accPage.getAccountSectionsHeaderList();
		System.out.println("Actual Account HeaderList" + actSecHeaderList);
		Assert.assertEquals(actSecHeaderList, AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}

	@Test
	public void isLogOutLinkExistTest() {
		Assert.assertEquals(accPage.isLogOutLinkExist(), true);
	}
}
