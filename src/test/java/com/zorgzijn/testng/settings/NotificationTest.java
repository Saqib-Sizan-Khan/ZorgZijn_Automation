package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class NotificationTest extends ZorgzijnBaseTest {

    PerformAction performAction = new PerformAction();


    @Test(groups = "notification-test")
    public void testNavigateToSettingsMenu() throws InterruptedException {
        performAction.tabNavigation(7);
    }

    @Test(groups = "notification-test", dependsOnMethods = "testNavigateToSettingsMenu")
    public void testNavigateToNotificationTab() throws InterruptedException {
        performAction.clickElement(By.xpath("//li[2]/a"));
    }

    @Test(groups = "notification-test", dependsOnMethods = "testNavigateToNotificationTab")
    public void testCreateNotification() throws InterruptedException {

        // Open notification creation form
        performAction.clickElement(By.xpath("//app-notification//div[1]/button"));

        // Form 1st step
        performAction.typeField(By.id("Titel"), "Automation Notification");
        performAction.typeField(By.xpath("//div[@class='ck ck-editor__main']/div"), RandomInput.text());
        performAction.shortWait();

        // Click Next Step
        performAction.clickElement(By.xpath("//form/div/button[1]"));
        performAction.shortWait();

        // 2nd step Choose employee
        performAction.typeField(By.id("simple-search"),"Sizan");
        performAction.shortWait();
        performAction.clickElement(By.xpath("//li[2]//input"));
        performAction.shortWait();

        // Confirm notification
        performAction.clickElement(By.xpath("//submit-button/button"));
        performAction.longWait();
    }

    @Test(groups = "notification-test", dependsOnMethods = "testCreateNotification")
    public void testViewNotification() throws InterruptedException {
        // See notification details
        performAction.clickElement(By.xpath("//app-notification/div/div/div/div[2]/div[2]/div[1]"));
        performAction.longWait();

        performAction.clickElement(By.xpath("//button[@type='button']"));
    }

    @Test(groups = "notification-test", dependsOnMethods = "testViewNotification")
    public void testDeleteNotification() throws InterruptedException {
        // Open delete dialog
        performAction.clickElement(By.xpath("//div[2]/div[1]/div[4]/button"));
        performAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
        performAction.shortWait();

        // Confirm delete
        performAction.clickElement(By.xpath("//button[@type='submit']"));
        performAction.shortWait();
    }
}
