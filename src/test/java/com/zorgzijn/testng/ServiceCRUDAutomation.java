package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.AutomationSetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ServiceCRUDAutomation extends AutomationSetupClass {

    private void clickElement(By locator) throws InterruptedException {
        driver.findElement(locator).click();
        Thread.sleep(1500);
    }

    private void sendKeysToElement(By locator, String keys) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(keys);
        Thread.sleep(1500);
    }

    private void sendKeysToTimeInput(By locator, String time, String mid) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        //element.clear();
        element.sendKeys(time);
        element.sendKeys(" "+mid);
        Thread.sleep(1500);
    }


    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        sendKeysToElement(By.id("E-mailadres"), "ssk123098@gmail.com");
        sendKeysToElement(By.id("Wachtwoord"), "Sizan@1999");
        clickElement(By.id("remember"));

        // Submit the login form
        clickElement(By.xpath("//button"));
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPlanningTab() throws InterruptedException {
        clickElement(By.xpath("//div/a[2]"));
        Thread.sleep(5000);
    }

    private void openServiceForm(boolean close, boolean cancel) throws InterruptedException {
        clickElement(By.xpath("//tbody/div[1]/tr[3]/td[2]"));

        if (close) {
            clickElement(By.xpath("//div[2]/div/button"));
        } else if (cancel) {
            clickElement(By.xpath("//form/div/button[2]"));
        }
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPlanningTab")
    public void createService() throws InterruptedException {
        //Check cancel and close button
//        openServiceForm(true, false);
//        openServiceForm(false, true);

        //Give input for creating service
        openServiceForm(false, false);
        clickElement(By.tagName("mat-select"));
        clickElement(By.xpath("//mat-option[5]"));
        sendKeysToElement(By.tagName("textarea"), "This is an automated text");

        //Submit form create the service
        clickElement(By.xpath("//form/div/button[1]"));
    }

    @Test(priority = 4, dependsOnMethods = "createService")
    public void modifyService() throws InterruptedException {

        //Open the modify service form
        clickElement(By.xpath("//tbody/div[1]/tr[3]/td[2]//button"));
        clickElement(By.cssSelector(".mat-mdc-menu-content button:nth-of-type(1)"));

        //Modify service info
        sendKeysToElement(By.id("Titel"), "Modified Automated Service");
        sendKeysToElement(By.tagName("textarea"), "The text is modified by selenium");
        clickElement(By.xpath("//color-picker/div/div[9]"));
        sendKeysToElement(By.id("Datum"), "18/12/2024");

        // Submit changes
        clickElement(By.xpath("//button[@type='submit']"));
    }

    @Test(priority = 5, dependsOnMethods = "modifyService")
    public void modifyService2() throws InterruptedException {

        //Open the modify service form
        clickElement(By.xpath("//tbody/div[1]/tr[3]/td[4]//button"));
        clickElement(By.cssSelector(".mat-mdc-menu-content button:nth-of-type(1)"));

        //Modify service info
        sendKeysToElement(By.xpath("//color-picker/div[2]//input[2]"), "#02e8cd");
        sendKeysToElement(By.id("Pauze"), "50");

        sendKeysToTimeInput(By.id("Starttijd"), "11:00", "PM");
        sendKeysToTimeInput(By.id("Eindtijd"), "8:00", "AM");

        // Submit changes
        clickElement(By.xpath("//button[@type='submit']"));
    }

    @Test(priority = 6, dependsOnMethods = "modifyService2")
    public void deleteService() throws InterruptedException {

        //Open the delete confirmation dialog
        clickElement(By.xpath("//tbody/div[1]/tr[3]/td[4]//button"));
        clickElement(By.cssSelector(".mat-mdc-menu-content button:nth-of-type(2)"));

        // Confirm Delete
        clickElement(By.xpath("//button[@type='submit']"));
    }
}
