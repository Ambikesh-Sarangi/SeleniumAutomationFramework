package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.ActionClass;

public class FormsPage {
	
	ActionClass action = new ActionClass();
	
	By panels = By.xpath("//div[@class='accordion-item']//button");
	By formsItems = By.xpath("//div[@id='collapseTwo']//li[@class='list-group-item']");
	
	
	public void openFormsPanel() {
		
		for(WebElement ele: action.getElements(panels)) {
			if(action.getText(ele).equalsIgnoreCase("Forms")) {
				action.click(ele);
			}
		}
	}
	
	public void clickPracticeForm() throws InterruptedException {
		
		for(WebElement ele: action.getElements(formsItems)) {
			if(action.getText(ele).trim().equalsIgnoreCase("Practice Form")) {
				action.click(ele);
			}
		}
	}
}
