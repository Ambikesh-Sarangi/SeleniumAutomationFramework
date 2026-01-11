package utils;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
