package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class ClientLocationTest extends ZorgzijnBaseTest {

    int locationCount;
    Random random = new Random();

    @Test(groups = "client-location-management")
    public void testNavigateToKlantenMenu() throws InterruptedException {
        PerformAction.tabNavigation(4);
    }

    @Test(groups = "client-location-management", dependsOnMethods = "testNavigateToKlantenMenu")
    public void testSearchClient() throws InterruptedException {
        Klanten.searchAndShowClient(Klanten.getClientName());
    }

    @Test(groups = "client-location-management", dependsOnMethods = "testSearchClient")
    public void testCreateLocations() throws InterruptedException {
        locationCount = 2 + random.nextInt(2);

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

    @Test(groups = "client-location-management", dependsOnMethods = "testCreateLocations")
    public void testModifyLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/client-location-detail")).size();

        for (int i = 0; i < 2; i++) {
            int location = 2 + random.nextInt(locationCount);

            //Activate location modification
            PerformAction.clickElement(By.xpath("//div["+ location +"]/client-location-detail/div/div[1]/button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change location info
            PerformAction.typeField(By.id("Naam"), "Location " + RandomInput.threeDigit());//Enter location name
            PerformAction.typeField(By.id("autocompleteInput"), RandomInput.streetName()); //Give Street Name
            PerformAction.typeField(By.id("Huisnummer"), RandomInput.threeDigit()); //Give House No
            PerformAction.typeField(By.id("Postcode"), RandomInput.postCode()); //Give Postcode
            PerformAction.typeField(By.id("Plaats"), RandomInput.place()); //Give Place

            //Submit Changes
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.shortWait();
        }
    }

    @Test(groups = "client-location-management", dependsOnMethods = "testModifyLocations")
    public void testDeleteLocations() throws InterruptedException {
        locationCount = driver.findElements(By.xpath("//div/client-location-detail")).size();

        for (int i = 0; i < 1; i++) {
            int location = 2 + random.nextInt(locationCount);

            //Open location delete dialog
            PerformAction.clickElement(By.xpath("//div["+ location +"]/client-location-detail/div/div[1]/button"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
            locationCount-=1;
            PerformAction.longWait();
        }
    }
}
