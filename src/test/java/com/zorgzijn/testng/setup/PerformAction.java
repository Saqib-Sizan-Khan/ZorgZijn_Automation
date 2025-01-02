package com.zorgzijn.testng.setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PerformAction {
    private static final int VERY_SHORT_WAIT = 750;
    private static final int SHORT_WAIT = 1500;
    private static final int LONG_WAIT = 2500;

    public static void shortWait () throws InterruptedException {
        Thread.sleep(SHORT_WAIT);
    }

    public static void longWait () throws InterruptedException {
        Thread.sleep(LONG_WAIT);
    }

    public static void veryLongWait () throws InterruptedException {
        Thread.sleep(LONG_WAIT*2);
    }

    public static void clickElement(By locator) throws InterruptedException {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in PerformAction.");
        }
        driver.findElement(locator).click();
        Thread.sleep(VERY_SHORT_WAIT);
    }

    public static void typeField(By locator, String keys) throws InterruptedException {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in PerformAction.");
        }
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        Thread.sleep(VERY_SHORT_WAIT);
        element.sendKeys(keys);
        Thread.sleep(VERY_SHORT_WAIT);
    }

    public static void clearField(By locator) throws InterruptedException {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in PerformAction.");
        }
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        Thread.sleep(SHORT_WAIT);
    }

    public static String getFieldText(By locator) throws InterruptedException {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized in PerformAction.");
        }
        WebElement element = WebDriverManager.getDriver().findElement(locator);
        System.out.println(element.getAttribute("value"));
        return element.getAttribute("value");
    }

    public static void waitForPageLoad() throws InterruptedException {
        Thread.sleep(LONG_WAIT);
    }
}
