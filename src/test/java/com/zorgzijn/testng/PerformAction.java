package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PerformAction extends AutomationSetupClass {
    private static final int SHORT_WAIT = 1500;
    private static final int LONG_WAIT = 2000;

    public static void shortWait () throws InterruptedException {
        Thread.sleep(SHORT_WAIT);
    }

    public static void longWait () throws InterruptedException {
        Thread.sleep(LONG_WAIT);
    }

    public static void clickElement(By locator) throws InterruptedException {
        driver.findElement(locator).click();
        Thread.sleep(SHORT_WAIT);
    }

    public static void sendKeysToElement(By locator, String keys) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(keys);
        Thread.sleep(SHORT_WAIT);
    }

    public static void waitForPageLoad() throws InterruptedException {
        Thread.sleep(LONG_WAIT);
    }
}
