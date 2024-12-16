package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class PlanningAutomation extends AutomationSetupClass {

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
        openServiceForm(true, false);
        openServiceForm(false, true);

        //Give input for creating service
        openServiceForm(false, false);
        clickElement(By.tagName("mat-select"));
        clickElement(By.xpath("//mat-option[5]"));
        sendKeysToElement(By.tagName("textarea"), "This is an automated text");

        //Submit form create the service
        clickElement(By.xpath("//form/div/button[1]"));
    }

    @Test(priority = 5, dependsOnMethods = "createService")
    public void modifyService() throws InterruptedException {
        clickElement(By.xpath("//div[2]/div/button"));
        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));

        sendKeysToElement(By.xpath("//div/div/input"), "Role Automation");

        // Update permissions
        clickElement(By.xpath("//li[2]/div/input"));
        clickElement(By.xpath("//li[4]/div/input"));
        clickElement(By.xpath("//li[5]/div/input"));
        clickElement(By.xpath("//li[7]/div/input"));

        // Submit changes
        clickElement(By.xpath("//button[@type='submit']"));
    }
//
//    @Test(priority = 6, dependsOnMethods = "modifyRole")
//    public void giveRoleAllAccess() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));
//
//        sendKeysToElement(By.xpath("//div/div/input"), "Role Automation with all access");
//
//        // Grant all permissions
//        clickElement(By.id("ALL"));
//
//        // Submit changes
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
//
//    private void openDeleteDialog() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
//    }
//
//    @Test(priority = 7, dependsOnMethods = "giveRoleAllAccess")
//    public void deleteRole() throws InterruptedException {
//        // Cancel the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//div[2]/div/div/div[1]/button"));
//
//        //Close the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//div[2]/div/div/div[3]/button[2]"));
//
//        // Confirm the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
}
