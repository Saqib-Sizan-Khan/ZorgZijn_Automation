package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Personeel;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class TimelineEmp extends AutomationSetupClass {

    int notesCount;
    Random random = new Random();

    @Test(priority = 1)
    public void login() throws InterruptedException {
        baseLogin();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPersoneelTab() throws InterruptedException {
        tabNavigation(5);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPersoneelTab")
    public void changeEmployeeFilter() throws InterruptedException {
        PerformAction.clearField(By.id("simple-search"));
        PerformAction.clickElement(By.xpath("//div[3]/button"));
        PerformAction.clickElement(By.id("mat-radio-4-input"));
        PerformAction.longWait();
    }

    @Test(priority = 4, dependsOnMethods = "changeEmployeeFilter")
    public void searchEmployee() throws InterruptedException {
        Personeel.searchAndShowEmployee("Dylan");
    }

    @Test(priority = 5, dependsOnMethods = "searchEmployee")
    public void addTimelineNotes() throws InterruptedException {
        // Randomly decide the number of notes to add (between 3 and 6)
        notesCount = 3 + random.nextInt(4);

        for (int i = 0; i < notesCount; i++) {
            PerformAction.typeField(By.xpath("//ckeditor/div[2]/div[2]/div"), RandomInput.text());
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.shortWait();
        }
    }

    @Test(priority = 6, dependsOnMethods = "addTimelineNotes")
    public void modifyTimelineNotes() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int noteNum = 1 + random.nextInt(notesCount);

            //Open edit timeline dialog
            PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div["+ noteNum +"]//button"));
            PerformAction.clickElement(By.xpath("//button[@role='menuitem'][1]"));

            //Modify timeline
            PerformAction.typeField(By.xpath("//form/app-ckeditor-field//div[2]/div[2]/div"), RandomInput.text());
            PerformAction.clickElement(By.xpath("//note-update-dialog//div[4]/button[1]"));
        }
    }

    @Test(priority = 7, dependsOnMethods = "modifyTimelineNotes")
    public void deleteTimeline() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            int noteNum = 1 + random.nextInt(notesCount);

            //Open edit timeline dialog
            PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div["+ noteNum +"]//button"));
            PerformAction.clickElement(By.xpath("//button[@role='menuitem'][2]"));

            //delete timeline
            PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
            notesCount-=1;
        }
    }
}
