package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import listeners.TestListener;
import pages.HomePage;

@Listeners(TestListener.class)
public class HomePageTest extends BaseClass{

	@Test
	public void homepageTest() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageLogo();
		Assert.assertTrue(homePage.verifyHomePageHeader().equalsIgnoreCase("Selenium - Automation Practice Form"));
	}
}
