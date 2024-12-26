package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Personeel;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class PersoneelAutomation2 extends AutomationSetupClass {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        baseLogin();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPersoneelTab() throws InterruptedException {
        tabNavigation(5);
    }

    @Test(priority = 6, dependsOnMethods = "navigateToPersoneelTab")
    public void changeEmployeeFilter() throws InterruptedException {
        PerformAction.clearField(By.id("simple-search"));
        PerformAction.clickElement(By.xpath("//div[3]/button"));
        PerformAction.clickElement(By.id("mat-radio-4-input"));
        PerformAction.longWait();
    }

    @Test(priority = 7, dependsOnMethods = "changeEmployeeFilter")
    public void searchEmployeeAgain() throws InterruptedException {
        Personeel.searchAndShowEmployee("Dylan");
    }

    @Test(priority = 8, dependsOnMethods = "searchEmployeeAgain")
    public void addTimeline() throws InterruptedException {
        PerformAction.sendKeysToElement(By.xpath("//ckeditor/div[2]/div[2]/div"), RandomInput.text());
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
    }

    @Test(priority = 9, dependsOnMethods = "addTimeline")
    public void editTimeline() throws InterruptedException {
        //Open edit timeline dialog
        PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div[1]//button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem'][1]"));

        //Modify timeline
        PerformAction.sendKeysToElement(By.xpath("//form/app-ckeditor-field//div[2]/div[2]/div"), RandomInput.text());
        PerformAction.clickElement(By.xpath("//note-update-dialog//div[4]/button[1]"));
    }

    @Test(priority = 10, dependsOnMethods = "editTimeline")
    public void deleteTimeline() throws InterruptedException {
        //Open delete timeline dialog
        PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div[1]//button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem'][2]"));

        //delete timeline
        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
    }

    @Test(priority = 11, dependsOnMethods = "deleteTimeline")
    public void deleteEmployee() throws InterruptedException {
        Personeel.openEmployeeMenu(3);
        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
        PerformAction.shortWait();
    }
}
