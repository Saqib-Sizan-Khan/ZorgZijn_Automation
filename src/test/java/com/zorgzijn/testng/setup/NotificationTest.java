package com.zorgzijn.testng.setup;

import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class NotificationTest extends ZorgzijnBaseTest {

    @Test(groups = "notification-test")
    public void testNavigateToNotificationTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[2]/a"));
    }

    @Test(groups = "notification-test", dependsOnMethods = "testNavigateToNotificationTab")
    public void createNotification() throws InterruptedException {

        // Open notification creation form
        PerformAction.clickElement(By.xpath("//app-notification//div[1]/button"));

        // Form 1st step
        PerformAction.typeField(By.id("Titel"), "Automation Notification");
        PerformAction.typeField(By.xpath("//div[@class='ck ck-editor__main']/div"), RandomInput.text());

        // Click Next Step

        driver.findElement(By.xpath("//form/div/button[1]")).click();
        Thread.sleep(2000);

        // 2nd step Choose employee
        driver.findElement(By.id("simple-search")).sendKeys("Sizan");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//li[2]//input")).click();
        Thread.sleep(1000);

        // Confirm notification
        driver.findElement(By.xpath("//submit-button/button")).click();
        Thread.sleep(3000);

    }

    @Test(priority = 4, dependsOnMethods = "createNotification")
    public void viewNotificationDetails() throws InterruptedException {
        // See notification details (Scenario 1)
        driver.findElement(By.xpath("//div[2]/div[2]/div[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(3000);

        // See notification details (Scenario 2)
        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5, dependsOnMethods = "viewNotificationDetails")
    public void deleteNotification() throws InterruptedException {
        // Delete notification
        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

}
