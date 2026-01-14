package base;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.ConfigReader;
import setup.TestSetup;
import utils.Log;

public class BaseClass extends TestSetup{
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger log = Log.getLogger(BaseClass.class);
	
	
	@BeforeMethod
	public void setup() throws Exception {
		
		log.info("Launching Browser");
		String browser = ConfigReader.getProp("browser");
		boolean headless = Boolean.parseBoolean(ConfigReader.getProp("headless"));
		
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(headless) options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
			driver.set(new ChromeDriver(options));
		} else if(browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if(headless) options.addArguments("--headless");
			driver.set(new FirefoxDriver(options));
		}
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().get(ConfigReader.getProp("baseUrl"));
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod
	public void tearDown() {
		log.info("Closing Browser");
		getDriver().quit();
		driver.remove();
	}

}
