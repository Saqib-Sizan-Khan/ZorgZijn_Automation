package com.zorgzijn.testng.personeel;

import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class TimelineEmployeeTest extends ZorgzijnBaseTest {

    int notesCount;
    Random random = new Random();


    @Test(groups = "employee-timeline-management")
    public void testNavigateToPersoneelMenu() throws InterruptedException {
        tabNavigation(5);
    }

    @Test(groups = "employee-timeline-management", dependsOnMethods = "testNavigateToPersoneelMenu")
    public void testChangeEmployeeFilter() throws InterruptedException {
        PerformAction.clearField(By.id("simple-search"));
        PerformAction.clickElement(By.xpath("//div[3]/button"));
        PerformAction.clickElement(By.id("mat-radio-4-input"));
        PerformAction.longWait();
    }

    @Test(groups = "employee-timeline-management", dependsOnMethods = "testChangeEmployeeFilter")
    public void testSearchEmployee() throws InterruptedException {
        Personeel.searchAndShowEmployee("Dylan");
    }

    @Test(groups = "employee-timeline-management", dependsOnMethods = "testSearchEmployee")
    public void testAddTimelineNotes() throws InterruptedException {
        // Randomly decide the number of notes to add (between 3 and 6)
        notesCount = 2 + random.nextInt(2);

        for (int i = 0; i < notesCount; i++) {
            PerformAction.typeField(By.xpath("//ckeditor/div[2]/div[2]/div"), RandomInput.text());
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.shortWait();
        }
    }

    @Test(groups = "employee-timeline-management", dependsOnMethods = "testAddTimelineNotes")
    public void testModifyTimelineNotes() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            int noteNum = 1 + random.nextInt(notesCount);

            //Open edit timeline dialog
            PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div["+ noteNum +"]//button"));
            PerformAction.clickElement(By.xpath("//button[@role='menuitem'][1]"));

            //Modify timeline
            PerformAction.typeField(By.xpath("//form/app-ckeditor-field//div[2]/div[2]/div"), RandomInput.text());
            PerformAction.clickElement(By.xpath("//note-update-dialog//div[4]/button[1]"));
            PerformAction.shortWait();
        }
    }

    @Test(groups = "employee-timeline-management", dependsOnMethods = "testModifyTimelineNotes")
    public void testDeleteTimeline() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
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
