package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BaseUI {

    public void waitAndClick(WebElement element){
        waitUntilClickable(20, element);
        element.click();
    }

    public void waitAndSendKeys(WebElement element, String keys){
        waitUntilVisible(20, element);
        element.sendKeys(keys);
    }

    /**
     * This method will wait for element to become visible
     * then it clears existing value and sends new keys
     * @param element - the input field
     * @param keys - the data to be sent
     */
    public void clearAndSendKeys(WebElement element, String keys){
        waitUntilVisible(20, element);
        element.clear();
        element.sendKeys(keys);
    }

    public void jsClick(WebElement element){
        waitUntilClickable(20, element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void jsSendKeys(WebElement element, String value){
        waitUntilVisible(20, element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='';", element);
        js.executeScript("arguments[0].value=arguments[1];", element, value);
    }

    public WebDriverWait explicitWait(int seconds){
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
    }

    public void waitUntilClickable(int seconds, WebElement element){
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilVisible(int seconds, WebElement element){
        new  WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void selectRandomOptionFromDropdown(WebElement dropdown, By optionsLocator){
        waitAndClick(dropdown);
        // explicitly waits for dropdown options to have more than 1 option
        explicitWait(20).until(ExpectedConditions.numberOfElementsToBeMoreThan(optionsLocator, 1));
        int randomIndex = new Random().nextInt(0, Driver.getDriver().findElements(optionsLocator).size());
        waitAndClick(Driver.getDriver().findElements(optionsLocator).get(randomIndex));
    }

    /**
     * This method clicks on the given element, switches to the newly opened tab
     * and prints its url.
     * @param element - is clicked to open new tab
     */
    public static void switchToNewTab(WebElement element){
        WebDriver driver = Driver.getDriver();
        String mainWindow = driver.getWindowHandle();

        element.click();

        for (String windowHandle : driver.getWindowHandles()){
            if (!windowHandle.equals(mainWindow)){
                driver.switchTo().window(windowHandle);
            }
        }

        System.out.println("Currently, the driver is on: " + driver.getCurrentUrl());
    }


    /**
     * Attempts to click an element using JavascriptExecutor with a retry mechanism.
     * @param element    The WebElement to click
     * @param maxRetries The maximum number of attempts
     * @return true if clicked successfully, false otherwise
     */
    public static boolean safeJSClick(WebElement element, int maxRetries) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        for (int i = 1; i <= maxRetries; i++) {
            try {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Successfully clicked element on attempt #" + i);
                return true; // Exit loop and method on success
            } catch (Exception e) {
                System.err.println("Attempt #" + i + " failed to click element: " + e.getMessage());
                // Optional: Add a short Thread.sleep(500) here if the page is mid-render
            }
        }

        System.err.println("Failed to click element after " + maxRetries + " attempts.");
        return false;
    }






}
