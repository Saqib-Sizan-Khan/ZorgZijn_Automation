package com.zorgzijn.testng.personeel;

import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.util.Random;

public class LocationEmployeeTest extends ZorgzijnBaseTest {

    int locationCount;
    Random random = new Random();


    @Test(groups = "employee-location-management")
    public void testNavigateToPersoneelMenu() throws InterruptedException {
        tabNavigation(5);
    }

    @Test(groups = "employee-location-management", dependsOnMethods = "testNavigateToPersoneelMenu")
    public void testSearchEmployee() throws InterruptedException {
        Personeel.searchAndShowEmployee("Dylan");
    }

    @Test(groups = "employee-location-management", dependsOnMethods = "testSearchEmployee")
    public void testNavigateToLocationTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//staff-tabs/div/div[3]"));
        PerformAction.shortWait();
    }

    @Test(groups = "employee-location-management", dependsOnMethods = "testNavigateToLocationTab")
    public void testAddLocations() throws InterruptedException {
        locationCount = 2 + random.nextInt(2);

        for (int i = 0; i < locationCount; i++) {
            boolean validLocation = false;

            while (!validLocation) {
                int contract = 1 + random.nextInt(2);

                //Open add location dialog
                PerformAction.clickElement(By.xpath("//staff-location/div/button"));
                PerformAction.shortWait();

                //Select location
                PerformAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
                int totalLocations = driver.findElements(By.xpath("//mat-option")).size();
                int pickLocation = 1 + random.nextInt(totalLocations);
                PerformAction.clickElement(By.xpath("//mat-option["+ pickLocation +"]"));

                //Set Employee fee
                PerformAction.typeField(By.id("Doordeweekse uren"), RandomInput.threeDigit());
                PerformAction.typeField(By.id("Weekenduren"), RandomInput.threeDigit());

                //Set Contract type
                PerformAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
                PerformAction.clickElement(By.xpath("//mat-option["+ contract +"]"));

                //Set Profile type
                PerformAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
                int totalProfiles = driver.findElements(By.xpath("//mat-option")).size();
                int pickProfile = 1 + random.nextInt(totalProfiles);
                PerformAction.clickElement(By.xpath("//mat-option["+ pickProfile +"]"));

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

    @Test(groups = "employee-location-management", dependsOnMethods = "testAddLocations")
    public void testModifyLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/staff-location-detail")).size();

        for (int i = 0; i < 2; i++) {
            int location = 1 + random.nextInt(locationCount);

            //Activate location modification
            PerformAction.clickElement(By.xpath("//div["+ location +"]/staff-location-detail//button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change Employee fee
            PerformAction.typeField(By.xpath("//div[1]/app-rate-input-field//input"), RandomInput.threeDigit());
            PerformAction.typeField(By.xpath("//div[2]/app-rate-input-field//input"), RandomInput.threeDigit());

            //Click submit
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "employee-location-management", dependsOnMethods = "testModifyLocations")
    public void testDeleteLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/staff-location-detail")).size();

        for (int i = 0; i < 1; i++) {
            int location = 1 + random.nextInt(locationCount);

            //Open delete dialog
            PerformAction.clickElement(By.xpath("//div["+ location +"]/staff-location-detail//button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
            locationCount-=1;
        }
    }

    @Test(groups = "employee-location-management", dependsOnMethods = "testDeleteLocations")
    public void testDeleteEmployee() throws InterruptedException {
        Personeel.openEmployeeMenu(3);
        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
        PerformAction.shortWait();
    }
}
