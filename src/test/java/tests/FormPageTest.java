package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.DataProviderUtil;
import listeners.TestListener;
import pages.FormsPage;

@Listeners(TestListener.class)
public class FormPageTest extends BaseClass{
	
	@Test(dataProvider = "practiceForm", dataProviderClass = DataProviderUtil.class)
	public void practiceFormTest(String Testcase, String Name, String Email, String Gender, String Mobile, String DOB, String Subjects, String Hobbies, String Address, String State, String City) throws InterruptedException {
		FormsPage formPage = new FormsPage();
		formPage.openFormsPanel();
		formPage.clickPracticeForm();
		Assert.assertTrue(formPage.verifyStudentRegistrationFormDisplayed().equalsIgnoreCase("Student Registration Form"));
		formPage.fillPracticeForm(Name, Email, Gender, Mobile, DOB, Subjects, Hobbies, Address, State, City);
		formPage.clickRegistrationFormLoginButton();
	}
	
	@Test
	public void registerationTest() {
		FormsPage formPage = new FormsPage();
		formPage.openFormsPanel();
		formPage.clickRegisterForm();
	}
	
}
