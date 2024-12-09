package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ProfileTypeAutomation extends LoginAutomation {

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToProfileTypeTab() throws InterruptedException {
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

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

//    @Test(priority = 4, dependsOnMethods = "createProfileType")
//    public void viewNotificationDetails() throws InterruptedException {
//        // See notification details (Scenario 1)
//        driver.findElement(By.xpath("//div[2]/div[2]/div[1]")).click();
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("//div[2]/button")).click();
//        Thread.sleep(3000);
//
//        // See notification details (Scenario 2)
//        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
//        Thread.sleep(2000);
//
//        driver.findElement(By.xpath("//div[2]/button")).click();
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 5, dependsOnMethods = "viewNotificationDetails")
//    public void deleteNotification() throws InterruptedException {
//        // Delete notification
//        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)")).click();
//        Thread.sleep(2000);
//
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        Thread.sleep(2000);
//    }

}
