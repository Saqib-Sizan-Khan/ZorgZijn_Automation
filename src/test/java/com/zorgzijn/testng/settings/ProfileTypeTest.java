package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProfileTypeTest extends ZorgzijnBaseTest {

    @Test(groups = "profiletype-test")
    public void testNavigateToSettingsMenu() throws InterruptedException {
        PerformAction.tabNavigation(7);
    }

    @Test(groups = "profiletype-test", dependsOnMethods = "testNavigateToSettingsMenu")
    public void testNavigateToProfileTypeTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[5]/a"));
    }

    @Test(groups = "profiletype-test", dependsOnMethods = "testNavigateToProfileTypeTab")
    public void testCreateProfileType() throws InterruptedException {

        PerformAction.clickElement(By.xpath("//app-profile-type/div/div/div/div/button"));

        WebElement createProfileButton = driver.findElement(By.xpath("//button"));

        // Open the Create Profile form
        createProfileButton.click();
        Thread.sleep(2000);

        // Test the close button
        WebElement closeButton = driver.findElements(By.cssSelector("form div button")).get(0);
        closeButton.click();
        Thread.sleep(2000);

        // Reopen the form and test the cancel button
        createProfileButton.click();
        Thread.sleep(2000);
        WebElement cancelButton = driver.findElements(By.cssSelector("form div button")).get(2);
        cancelButton.click();
        Thread.sleep(2000);

        // Reopen the form and complete the Profile Type creation
        createProfileButton.click();
        Thread.sleep(2000);

        // Fill out the form
        driver.findElement(By.xpath("//input")).sendKeys("Automation Profile");
        driver.findElement(By.tagName("mat-select")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//mat-option[2]")).click();
        Thread.sleep(2000);

        // Submit the form
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 4, dependsOnMethods = "createProfileType")
    public void modifyProfileType() throws InterruptedException {
        // Open the menu for the first profile type
        driver.findElement(By.xpath("//td/button")).click();
        Thread.sleep(2000);

        // Select the Edit option
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        // Modify the Profile Name
        WebElement profileName = driver.findElement(By.xpath("//input"));
        profileName.clear();
        profileName.sendKeys("Profile Automation");
        Thread.sleep(2000);

        // Modify the Profile Category
        driver.findElement(By.tagName("mat-select")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//mat-option[1]")).click();
        Thread.sleep(2000);

        // Submit the updated Profile Type
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5, dependsOnMethods = "modifyProfileType")
    public void deleteProfileType() throws InterruptedException {
        WebElement menuButton = driver.findElement(By.xpath("//td/button"));

        // Open the menu and select the Delete option
        menuButton.click();
        Thread.sleep(2000);
        WebElement deleteOption = driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
        deleteOption.click();
        Thread.sleep(2000);

        // Cancel the delete action
        driver.findElement(By.xpath("//div[2]/div/div/div[1]/button")).click();
        Thread.sleep(2000);

        // Reattempt delete and confirm using alternative options
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
