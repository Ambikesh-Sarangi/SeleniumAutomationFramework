package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class ActionClass {

    private WebDriverWait wait;

    public ActionClass() {
        // Uses ThreadLocal driver safely
        this.wait = new WebDriverWait(BaseClass.getDriver(), Duration.ofSeconds(15));
    }

    /* -------------------- CLICK -------------------- */

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    
    /* -------------------- GET ELEMENTS -------------------- */
    
    public List<WebElement> getElements(By locator) {
    	return BaseClass.getDriver().findElements(locator);
    }

    /* -------------------- SEND KEYS -------------------- */

    public void type(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void type(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    /* -------------------- GET TEXT -------------------- */

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    
    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    
    /* -------------------- GET ATTRIBUTE -------------------- */
    
    public String getAttribute(By locator, String attributeName) {
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attributeName);
    }

    /* -------------------- VISIBILITY -------------------- */

    public boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /* -------------------- WAIT -------------------- */

    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /* -------------------- NAVIGATION -------------------- */

    public void navigateTo(String url) {
        BaseClass.getDriver().get(url);
    }

    public String getTitle() {
        return BaseClass.getDriver().getTitle();
    }
}
