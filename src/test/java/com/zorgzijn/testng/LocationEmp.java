package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Personeel;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
        PerformAction.shortWait();
    }

    @Test(priority = 5, dependsOnMethods = "navigateToLocationOption")
    public void addLocations() throws InterruptedException {
        locationCount = 3 + random.nextInt(4);

        for (int i = 0; i < locationCount; i++) {
            boolean validLocation = false;

            while (!validLocation) {
                int location = 1 + random.nextInt(25);
                int contract = 1 + random.nextInt(2);
                int profile = 1 + random.nextInt(19);

                //Open add location dialog
                PerformAction.clickElement(By.xpath("//staff-location/div/button"));
                PerformAction.shortWait();

                //Select location
                PerformAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
                PerformAction.clickElement(By.xpath("//mat-option["+ location +"]"));

                //Set Employee fee
                PerformAction.typeField(By.id("Doordeweekse uren"), RandomInput.feePerHour());
                PerformAction.typeField(By.id("Weekenduren"), RandomInput.feePerHour());

                //Set Contract and Profile type
                PerformAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
                PerformAction.clickElement(By.xpath("//mat-option["+ contract +"]"));
                PerformAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
                PerformAction.clickElement(By.xpath("//mat-option["+ profile +"]"));

                //Click submit
                PerformAction.clickElement(By.xpath("//button[@type='submit']"));
                PerformAction.longWait();

                // Check for alert message
                try {
                    String alert = driver.findElement(By.xpath("//alert//p")).getText();

                    if (alert.equals("Medewerker die al aan deze locatie is toegewezen.")) {
                        System.out.println("Same location picked, trying again...");
                        PerformAction.clickElement(By.xpath("//form/div[3]/button"));
                    } else {
                        validLocation = true;
                    }
                } catch (NoSuchElementException e) {
                    validLocation = true;
                }
            }
        }
    }

    @Test(priority = 6, dependsOnMethods = "addLocations")
    public void modifyLocations() throws InterruptedException {
        int locationCount = driver.findElements(By.xpath("//div/staff-location-detail")).size();

        for (int i = 0; i < 3; i++) {
            int location = 1 + random.nextInt(locationCount);

            //Activate location modification
            PerformAction.clickElement(By.xpath("//div["+ location +"]/staff-location-detail//button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change Employee fee
            PerformAction.typeField(By.xpath("//div[1]/app-rate-input-field//input"), RandomInput.feePerHour());
            PerformAction.typeField(By.xpath("//div[2]/app-rate-input-field//input"), RandomInput.feePerHour());

            //Click submit
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.longWait();
        }
    }

    @Test(priority = 7, dependsOnMethods = "modifyLocations")
    public void deleteLocations() throws InterruptedException {
        int locationCount = driver.findElements(By.xpath("//div/staff-location-detail")).size();

        for (int i = 0; i < 2; i++) {
            int location = 1 + random.nextInt(locationCount);

            //Open delete dialog
            PerformAction.clickElement(By.xpath("//div["+ location +"]/staff-location-detail//button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
            locationCount-=1;
        }
    }

    @Test(priority = 8, dependsOnMethods = "addLocations")
    public void deleteEmployee() throws InterruptedException {
        Personeel.openEmployeeMenu(3);
        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
        PerformAction.shortWait();
    }
}
