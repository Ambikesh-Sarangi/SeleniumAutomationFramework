package pages;

import org.openqa.selenium.By;

import utils.ActionClass;

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
	By practiceFormHobbies = By.xpath("//input[@type='checkbox']");
	By practiceFormPicture = By.id("picture");
	By practiceFormAddress = By.xpath("//textarea[@placeholder='Currend Address']");
	By practiceFormState = By.id("state");
	By practiceFormCity = By.id("City");
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
		action.type(practiceFormDOB, dob);
		action.type(practiceFormSubjects, subjects);
		//Add Hobbies
		action.type(practiceFormAddress, address);
	}
}
