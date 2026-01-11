package pages;

import org.openqa.selenium.By;

import utils.ActionClass;

public class HomePage {
	
	ActionClass action = new ActionClass();
	
	By pageLogo = By.xpath("//*[@class='logo-desktop']");
	By pageHeader = By.xpath("//header//h1");
	
	public void verifyHomePageLogo() {
		action.isDisplayed(pageLogo);
	}
	
	public String verifyHomePageHeader() {
		return action.getText(pageHeader);
	}

}
