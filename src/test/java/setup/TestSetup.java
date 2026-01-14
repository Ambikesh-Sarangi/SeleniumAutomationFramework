package setup;

import org.testng.annotations.BeforeSuite;

import utils.ClearScreenshotsFolder;

public class TestSetup {
	
	@BeforeSuite
	public void beforeSuite() {
	    ClearScreenshotsFolder.cleanScreenshotFolder();
	}

}
