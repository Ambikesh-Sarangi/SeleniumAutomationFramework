package pages;

import org.openqa.selenium.By;

import utils.ActionClass;
import utils.ExtentManager;

public class FormsPage {
	
	ActionClass action = new ActionClass();
	
	By formsPanel = By.xpath("//div[@class='accordion-item']//button[contains(text(), 'Forms')]");
	By practiceFormButton = By.xpath("//li[@class='list-group-item' and normalize-space()='Practice Form']");
	By practiceForm = By.id("practiceForm");
	By practiceFormHeader = By.xpath("//form[@id='practiceForm']//h1");
	By practiceFormName = By.id("name");
	By practiceFormEmail = By.id("email");
	By practiceFormMaleGenderRadioButton = By.xpath("//input[@id='gender']");
	By practiceFormMobile = By.id("mobile");
	By practiceFormDOB = By.id("dob");
	By practiceFormSubjects = By.id("subjects");
	By practiceFormHobbies(String hobbyName) { return By.xpath("//label[normalize-space()='" + hobbyName + "']/preceding-sibling::input");}
	By practiceFormPicture = By.id("picture");
	By practiceFormAddress = By.xpath("//textarea[@placeholder='Currend Address']");
	By practiceFormState = By.id("state");
	By practiceFormCity = By.id("city");
	By practiceFormLoginButton = By.xpath("//input[@type='submit']");
	
	public void openFormsPanel() {
		action.isDisplayed(formsPanel);
		action.click(formsPanel);
	}
	
	public void clickPracticeForm() throws InterruptedException {
		action.isDisplayed(practiceFormButton);
		action.click(practiceFormButton);
	}
	
	public String verifyStudentRegistrationFormDisplayed() {
		action.isDisplayed(practiceForm);
		return action.getText(practiceFormHeader);
	}
	
	public void fillPracticeForm(String name, String email, String gender, String mobileNumber, String dob, String subjects, String hobbies, String address, String state, String city) {
		action.type(practiceFormName, name);
		action.type(practiceFormEmail, email);
		action.click(practiceFormMaleGenderRadioButton);
		action.type(practiceFormMobile, mobileNumber);
		action.type(practiceFormDOB, dob);
		action.type(practiceFormSubjects, subjects);
		
		String[] hobbiesList = hobbies.split(",");
		for(String word: hobbiesList) {
			String trimmedHobby = word.trim();
			By checkbox = practiceFormHobbies(trimmedHobby);
			if(action.isDisplayed(checkbox)) {
				action.click(checkbox);
			} else {
				ExtentManager.getTest().warning("Hobby not found in UI, skipped: " + trimmedHobby);
			}
		}
		action.type(practiceFormAddress, address);
		action.selectByValue(practiceFormState, state);
		action.selectByValue(practiceFormCity, city);
	}
}
