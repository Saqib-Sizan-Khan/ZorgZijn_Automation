package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Personeel;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class LocationEmp extends AutomationSetupClass {

    int locationCount;
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
    public void searchEmployee() throws InterruptedException {
        Personeel.searchAndShowEmployee("Dylan");
    }

    @Test(priority = 4, dependsOnMethods = "searchEmployee")
    public void navigateToLocationOption() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//staff-tabs/div/div[3]"));
    }

    @Test(priority = 5, dependsOnMethods = "navigateToLocationOption")
    public void addLocations() throws InterruptedException {
        // Randomly decide the number of location to add (between 3 and 6)
        locationCount = 3 + random.nextInt(4);

        //Open add location dialog
        PerformAction.clickElement(By.xpath("//staff-location/div/button"));

        //Select location
        PerformAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
        PerformAction.clickElement(By.xpath("//mat-option[3]"));

        //Set Employee fee
        PerformAction.typeField(By.id("Doordeweekse uren"), RandomInput.feePerHour());
        PerformAction.typeField(By.id("Weekenduren"), RandomInput.feePerHour());

        //Set Contract and Profile type
        PerformAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[1]"));
        PerformAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
        PerformAction.clickElement(By.xpath("//mat-option[4]"));

        //Click submit
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));

//        for (int i = 0; i < locationCount; i++) {
//            PerformAction.typeField(By.xpath("//ckeditor/div[2]/div[2]/div"), RandomInput.text());
//            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
//            PerformAction.shortWait();
//        }
    }

//    @Test(priority = 6, dependsOnMethods = "addLocations")
//    public void modifyTimelineNotes() throws InterruptedException {
//        for (int i = 0; i < 3; i++) {
//            int noteNum = 1 + random.nextInt(locationCount);
//
//            //Open edit timeline dialog
//            PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div["+ noteNum +"]//button"));
//            PerformAction.clickElement(By.xpath("//button[@role='menuitem'][1]"));
//
//            //Modify timeline
//            PerformAction.typeField(By.xpath("//form/app-ckeditor-field//div[2]/div[2]/div"), RandomInput.text());
//            PerformAction.clickElement(By.xpath("//note-update-dialog//div[4]/button[1]"));
//        }
//    }
//
//    @Test(priority = 7, dependsOnMethods = "modifyTimelineNotes")
//    public void deleteTimeline() throws InterruptedException {
//        for (int i = 0; i < 2; i++) {
//            int noteNum = 1 + random.nextInt(locationCount);
//
//            //Open edit timeline dialog
//            PerformAction.clickElement(By.xpath("//staff-timeline/div/div[2]/div["+ noteNum +"]//button"));
//            PerformAction.clickElement(By.xpath("//button[@role='menuitem'][2]"));
//
//            //delete timeline
//            PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
//            locationCount-=1;
//        }
//    }
//
//    @Test(priority = 8, dependsOnMethods = "deleteTimeline")
//    public void deleteEmployee() throws InterruptedException {
//        Personeel.openEmployeeMenu(3);
//        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
//        PerformAction.shortWait();
//    }
}
