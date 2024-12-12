package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class UserAutomation extends AutomationSetupClass {

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
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToUserTab() throws InterruptedException {
        clickElement(By.xpath("//li[6]/a"));
    }

    private void openUserForm(boolean close, boolean cancel) throws InterruptedException {
        clickElement(By.xpath("//button"));

        if (close) {
            WebElement closeButton = driver.findElements(By.cssSelector("form div button")).get(0);
            closeButton.click();
        } else if (cancel) {
            WebElement cancelButton = driver.findElements(By.cssSelector("form div button")).get(2);
            cancelButton.click();
        }
        Thread.sleep(1500);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToUserTab")
    public void createUser() throws InterruptedException {
        openUserForm(true, false);
        openUserForm(false, true);

        openUserForm(false, false);

        sendKeysToElement(By.id("Voornaam"), "Selenium");
        sendKeysToElement(By.id("Tussenvoegsel"), "Automated");
        sendKeysToElement(By.id("Achternaam"), "User");
        sendKeysToElement(By.id("E-mailadres"), "sizan.sqa@gmail.com");

        clickElement(By.tagName("mat-select"));
        clickElement(By.cssSelector("mat-option:nth-of-type(1)"));

        // Submit the form
        clickElement(By.xpath("//button[@type='submit']"));
    }

//    @Test(priority = 4, dependsOnMethods = "createRole")
//    public void searchRole() throws InterruptedException {
//        sendKeysToElement(By.xpath("//input[@type='search']"), "Automation");
//        Thread.sleep(3000); // Simulate search delay
//
//        // Clear the search field
//        driver.findElement(By.xpath("//input[@type='search']")).clear();
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 5, dependsOnMethods = "searchRole")
//    public void modifyRole() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));
//
//        sendKeysToElement(By.xpath("//div/div/input"), "Role Automation");
//
//        // Update permissions
//        clickElement(By.xpath("//li[2]/div/input"));
//        clickElement(By.xpath("//li[4]/div/input"));
//        clickElement(By.xpath("//li[5]/div/input"));
//        clickElement(By.xpath("//li[7]/div/input"));
//
//        // Submit changes
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
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
