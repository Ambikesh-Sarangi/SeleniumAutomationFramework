package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import listeners.TestListener;
import pages.FormsPage;

@Listeners(TestListener.class)
public class FormPageTest extends BaseClass{
	
	@Test
	public void practiceFormTest() throws InterruptedException {
		FormsPage formPage = new FormsPage();
		formPage.openFormsPanel();
		formPage.clickPracticeForm();
	}

}
