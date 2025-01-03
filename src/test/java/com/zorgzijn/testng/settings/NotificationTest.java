package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class NotificationTest extends ZorgzijnBaseTest {

    @Test(groups = "notification-test")
    public void testNavigateToSettingsMenu() throws InterruptedException {
        tabNavigation(7);
    }

    @Test(groups = "notification-test", dependsOnMethods = "testNavigateToSettingsMenu")
    public void testNavigateToNotificationTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[2]/a"));
    }

    @Test(groups = "notification-test", dependsOnMethods = "testNavigateToNotificationTab")
    public void testCreateNotification() throws InterruptedException {

        // Open notification creation form
        PerformAction.clickElement(By.xpath("//app-notification//div[1]/button"));

        // Form 1st step
        PerformAction.typeField(By.id("Titel"), "Automation Notification");
        PerformAction.typeField(By.xpath("//div[@class='ck ck-editor__main']/div"), RandomInput.text());
        PerformAction.shortWait();

        // Click Next Step
        PerformAction.clickElement(By.xpath("//form/div/button[1]"));
        PerformAction.shortWait();

        // 2nd step Choose employee
        PerformAction.typeField(By.id("simple-search"),"Sizan");
        PerformAction.shortWait();
        PerformAction.clickElement(By.xpath("//li[2]//input"));
        PerformAction.shortWait();

        // Confirm notification
        PerformAction.clickElement(By.xpath("//submit-button/button"));
        PerformAction.longWait();
    }

    @Test(groups = "notification-test", dependsOnMethods = "testCreateNotification")
    public void testViewNotification() throws InterruptedException {
        // See notification details
        PerformAction.clickElement(By.xpath("//app-notification/div/div/div/div[2]/div[2]/div[1]"));
        PerformAction.longWait();

        PerformAction.clickElement(By.xpath("//button[@type='button']"));
    }

    @Test(groups = "notification-test", dependsOnMethods = "testViewNotification")
    public void testDeleteNotification() throws InterruptedException {
        // Open delete dialog
        PerformAction.clickElement(By.xpath("//div[2]/div[1]/div[4]/button"));
        PerformAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
        PerformAction.shortWait();

        // Confirm delete
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.shortWait();
    }
}
