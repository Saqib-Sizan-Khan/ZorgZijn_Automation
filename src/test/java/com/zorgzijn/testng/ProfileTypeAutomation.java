package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProfileTypeAutomation extends AutomationSetupClass {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
        driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
        driver.findElement(By.id("remember")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToProfileTypeTab() throws InterruptedException {

        driver.findElement(By.xpath("//li[5]/a")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToProfileTypeTab")
    public void createProfileType() throws InterruptedException {
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input")).sendKeys("Automation Profile");
        driver.findElement(By.tagName("mat-select")).click();
        driver.findElement(By.xpath("//mat-option[2]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 4, dependsOnMethods = "createProfileType")
    public void modifyProfileType() throws InterruptedException {

        driver.findElement(By.xpath("//td/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        WebElement profileName = driver.findElement(By.xpath("//input"));
        profileName.clear();
        profileName.sendKeys("Profile Automation");

        driver.findElement(By.tagName("mat-select")).click();
        driver.findElement(By.xpath("//mat-option[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5, dependsOnMethods = "modifyProfileType")
    public void deleteProfileType() throws InterruptedException {
        driver.findElement(By.xpath("//td/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

}
