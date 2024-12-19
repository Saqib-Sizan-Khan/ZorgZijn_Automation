package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ServiceRegisterAutomation extends AutomationSetupClass {

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

    @Test(priority = 3, dependsOnMethods = "navigateToPlanningTab")
    public void createService() throws InterruptedException {

        //Give input for creating service
        clickElement(By.xpath("//tbody/div[1]/tr[3]/td[4]"));
        clickElement(By.tagName("mat-select"));
        clickElement(By.xpath("//mat-option[5]"));
        sendKeysToElement(By.tagName("textarea"), "This is an automated text");

        //Submit form create the service
        clickElement(By.xpath("//form/div/button[1]"));
    }

    @Test(priority = 4, dependsOnMethods = "createService")
    public void navigateToTimeRegistrationTab() throws InterruptedException {
        clickElement(By.xpath("//div/a[3]"));
    }

    @Test(priority = 5, dependsOnMethods = "navigateToTimeRegistrationTab")
    public void registerService() throws InterruptedException {

        //Change date
        clickElement(By.xpath("//button[1]"));

        //Registered that service
        clickElement(By.xpath("//tr[2]//button[2]"));
    }

//    @Test(priority = 6, dependsOnMethods = "registerService")
//    public void navigateToInvoiceTab() throws InterruptedException {
//        clickElement(By.xpath("//div/a[6]"));
//    }
//
//    @Test(priority = 7, dependsOnMethods = "navigateToInvoiceTab")
//    public void invoiceService() throws InterruptedException {
//
//        clickElement(By.xpath("//div/p"));
//        Thread.sleep(3000);
//
//        WebElement element = driver.findElement(By.xpath("//div/span[24]"));
//
//        System.out.println("The text is: "+element);
//        element.click();
//    }
}
