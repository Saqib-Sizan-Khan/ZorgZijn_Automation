package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ClientTest extends ZorgzijnBaseTest {

    PerformAction performAction = new PerformAction();


    @Test(groups = "client-management")
    public void testNavigateToKlantenMenu() throws InterruptedException {
        performAction.tabNavigation(4);
    }

    @Test(groups = "client-management", dependsOnMethods = "testNavigateToKlantenMenu")
    public void testCreateClient() throws InterruptedException {
        performAction.clickElement(By.tagName("button"));

        // Enter KVK
        performAction.typeField(By.id("KVK"), "63888076");

        //Type Email and Phone
        performAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        performAction.typeField(By.id("E-mailadres"), RandomInput.email());

        //Give Client Details
        performAction.typeField(By.id("BTW-nummer"), "865845621B01");
        performAction.typeField(By.id("Rekeningnummer"), "02ABNA0123456789");
        performAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        //Select VAT type
        performAction.clickElement(By.tagName("mat-select"));
        performAction.clickElement(By.xpath("//mat-option[2]"));

        //Checked relationship code
        performAction.clickElement(By.xpath("//input[@type='checkbox']"));

        //Get Client Name
        Klanten.setClientName(performAction.getFieldText(By.id("Naam bedrijf")));

        //Click submit button
        performAction.clickElement(By.xpath("//button[@type='submit']"));

        performAction.longWait();
    }

    @Test(groups = "client-management", dependsOnMethods = "testCreateClient")
    public void testSearchClient() throws InterruptedException {
        System.out.println(Klanten.getClientName());
        Klanten.searchAndShowClient(Klanten.getClientName());
    }

    @Test(groups = "client-management", dependsOnMethods = "testSearchClient")
    public void testModifyClient() throws InterruptedException {
        Klanten.openClientMenu(2);

        //Change KVK
        performAction.typeField(By.id("KVK"), "91532906");

        //Change Email and Phone
        performAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        performAction.typeField(By.id("E-mailadres"), RandomInput.email());

        //Change Fee
        performAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        //Change VAT type
        performAction.clickElement(By.tagName("mat-select"));
        performAction.clickElement(By.xpath("//mat-option[1]"));

        //Get Client Name
        Klanten.setClientName(performAction.getFieldText(By.id("Naam bedrijf")));

        //Submit Changes
        performAction.clickElement(By.xpath("//submit-button/button"));

        performAction.longWait();
    }
}
