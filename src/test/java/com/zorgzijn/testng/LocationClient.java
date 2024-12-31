package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Klanten;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class LocationClient extends AutomationSetupClass {

    int locationCount;
    Random random = new Random();

    @Test(priority = 1)
    public void login() throws InterruptedException {
        baseLogin();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToKlantenTab() throws InterruptedException {
        tabNavigation(4);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToKlantenTab")
    public void searchClient() throws InterruptedException {
        Klanten.searchAndShowClient(Klanten.getClientName());
    }

    @Test(priority = 4, dependsOnMethods = "searchClient")
    public void createLocations() throws InterruptedException {
        locationCount = 3 + random.nextInt(4);

        for (int i = 0; i < locationCount; i++) {

            PerformAction.clickElement(By.xpath("//client-location/div/div/button"));

            PerformAction.typeField(By.id("Naam"), "Location "+ (i+1));//Enter location name
            PerformAction.typeField(By.id("autocompleteInput"), RandomInput.streetName()); //Give Street Name
            PerformAction.typeField(By.id("Huisnummer"), RandomInput.threeDigit()); //Give House No
            PerformAction.typeField(By.id("Postcode"), RandomInput.postCode()); //Give Postcode
            PerformAction.typeField(By.id("Plaats"), RandomInput.place()); //Give Place

            //Click submit button
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.shortWait();
        }
    }

    @Test(priority = 5, dependsOnMethods = "createLocations")
    public void modifyLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/client-location-detail")).size();

        for (int i = 0; i < 3; i++) {
            int location = 2 + random.nextInt(locationCount);

            //Activate location modification
            PerformAction.clickElement(By.xpath("//div["+ location +"]/client-location-detail/div/div[1]/button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            PerformAction.typeField(By.id("Naam"), "Location " + RandomInput.threeDigit());//Enter location name
            PerformAction.typeField(By.id("autocompleteInput"), RandomInput.streetName()); //Give Street Name
            PerformAction.typeField(By.id("Huisnummer"), RandomInput.threeDigit()); //Give House No
            PerformAction.typeField(By.id("Postcode"), RandomInput.postCode()); //Give Postcode
            PerformAction.typeField(By.id("Plaats"), RandomInput.place()); //Give Place

            //Click submit button
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.shortWait();
        }
    }

    @Test(priority = 6, dependsOnMethods = "modifyLocations")
    public void deleteLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/client-location-detail")).size();

        for (int i = 0; i < 2; i++) {
            int location = 2 + random.nextInt(locationCount);

            //Open location delete dialog
            PerformAction.clickElement(By.xpath("//div["+ location +"]/client-location-detail/div/div[1]/button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Click submit button
            PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
            PerformAction.longWait();
            locationCount-=1;
        }
    }

    @Test(priority = 7, dependsOnMethods = "deleteLocations")
    public void deleteClient() throws InterruptedException {
        Klanten.openClientMenu(3);
        PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
        PerformAction.shortWait();
    }
}
