package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class RoleAutomation extends AutomationSetupClass {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        // Enter login credentials
        driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
        driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
        driver.findElement(By.id("remember")).click();
        Thread.sleep(2000);

        // Submit the login form
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToRoleTab() throws InterruptedException {
        // Open the Role tab
        driver.findElement(By.xpath("//li[7]/a")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToRoleTab")
    public void createRole() throws InterruptedException {
        WebElement createRoleButton = driver.findElement(By.xpath("//button"));

        // Open the Create Role form
        createRoleButton.click();
        Thread.sleep(2000);

        // Test the close button
        WebElement closeButton = driver.findElements(By.cssSelector("form div button")).get(0);
        closeButton.click();
        Thread.sleep(2000);

        // Reopen the form and test the cancel button
        createRoleButton.click();
        Thread.sleep(2000);
        WebElement cancelButton = driver.findElements(By.cssSelector("form div button")).get(2);
        cancelButton.click();
        Thread.sleep(2000);

        // Reopen the form and fill it out
        createRoleButton.click();
        Thread.sleep(2000);

        // Fill in the Role Name
        driver.findElement(By.xpath("//div/div/input")).sendKeys("Automation Role");
        Thread.sleep(2000);

        // Select access permissions
        driver.findElement(By.xpath("//li[1]/div/input")).click();
        driver.findElement(By.xpath("//li[3]/div/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[5]/div/input")).click();
        driver.findElement(By.xpath("//li[7]/div/input")).click();
        Thread.sleep(2000);

        // Submit the form
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 4, dependsOnMethods = "createRole")
    public void searchRole() throws InterruptedException {
        WebElement searchField = driver.findElement(By.xpath("//input[@type='search']"));

        // Search for a role by name
        searchField.sendKeys("Automation");
        Thread.sleep(3000);

        // Clear the search field
        searchField.sendKeys("");
        Thread.sleep(3000);
    }

    @Test(priority = 5, dependsOnMethods = "searchRole")
    public void modifyRole() throws InterruptedException {
        // Open the menu for the first role
        driver.findElement(By.xpath("//div[2]/div/button")).click();
        Thread.sleep(2000);

        // Select the Edit option
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        // Modify the Role Name
        WebElement roleName = driver.findElement(By.xpath("//div/div/input"));
        roleName.clear();
        roleName.sendKeys("Role Automation");
        Thread.sleep(2000);

        // Update access permissions
        driver.findElement(By.xpath("//li[2]/div/input")).click();
        driver.findElement(By.xpath("//li[4]/div/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[5]/div/input")).click();
        driver.findElement(By.xpath("//li[7]/div/input")).click();
        Thread.sleep(2000);

        // Submit the changes
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 6, dependsOnMethods = "modifyRole")
    public void giveRoleAllAccess() throws InterruptedException {
        // Open the menu for the first role
        driver.findElement(By.xpath("//div[2]/div/button")).click();
        Thread.sleep(2000);

        // Select the Edit option
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        // Modify the Role Name
        WebElement roleName = driver.findElement(By.xpath("//div/div/input"));
        roleName.clear();
        roleName.sendKeys("Role Automation with all access");
        Thread.sleep(2000);

        // Update access permissions
        driver.findElement(By.id("ALL")).click();
        Thread.sleep(2000);

        // Submit the changes
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 7, dependsOnMethods = "giveRoleAllAccess")
    public void deleteRole() throws InterruptedException {
        WebElement menuButton = driver.findElement(By.xpath("//div[2]/div/button"));

        // Open the menu and select the Delete option
        menuButton.click();
        Thread.sleep(2000);
        WebElement deleteOption = driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
        deleteOption.click();
        Thread.sleep(2000);

        // Cancel the delete action
        driver.findElement(By.xpath("//div[2]/div/div/div[1]/button")).click();
        Thread.sleep(2000);

        // Retry delete and confirm with different options
        menuButton.click();
        Thread.sleep(2000);
        deleteOption.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[2]/div/div/div[3]/button[2]")).click();
        Thread.sleep(2000);

        // Final delete confirmation
        menuButton.click();
        Thread.sleep(2000);
        deleteOption.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }
}