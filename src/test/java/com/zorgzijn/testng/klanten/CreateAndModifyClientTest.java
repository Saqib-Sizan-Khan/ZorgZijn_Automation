package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CreateAndModifyClientTest extends ZorgzijnBaseTest {

    @Test(groups = "client-management")
    public void testNavigateToKlantenMenu() throws InterruptedException {
        tabNavigation(4);
    }

    @Test(groups = "client-management", dependsOnMethods = "testNavigateToKlantenMenu")
    public void testCreateClient() throws InterruptedException {
        PerformAction.clickElement(By.tagName("button"));

        // Enter KVK
        PerformAction.typeField(By.id("KVK"), "63888076");

        //Type Email and Phone
        PerformAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.typeField(By.id("E-mailadres"), RandomInput.email());

        //Give Client Details
        PerformAction.typeField(By.id("BTW-nummer"), "865845621B01");
        PerformAction.typeField(By.id("Rekeningnummer"), "02ABNA0123456789");
        PerformAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        //Select VAT type
        PerformAction.clickElement(By.tagName("mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[2]"));

        //Checked relationship code
        PerformAction.clickElement(By.xpath("//input[@type='checkbox']"));

        //Get Client Name
        Klanten.setClientName(PerformAction.getFieldText(By.id("Naam bedrijf")));

        //Click submit button
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));

        PerformAction.longWait();
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
        PerformAction.typeField(By.id("KVK"), "91532906");

        //Change Email and Phone
        PerformAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.typeField(By.id("E-mailadres"), RandomInput.email());

        //Change Fee
        PerformAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        //Change VAT type
        PerformAction.clickElement(By.tagName("mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[1]"));

        //Get Client Name
        Klanten.setClientName(PerformAction.getFieldText(By.id("Naam bedrijf")));

        //Submit Changes
        PerformAction.clickElement(By.xpath("//submit-button/button"));

        PerformAction.longWait();
    }
}
