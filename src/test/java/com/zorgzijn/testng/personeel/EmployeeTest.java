package com.zorgzijn.testng.personeel;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;

import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class EmployeeTest extends ZorgzijnBaseTest {

    PerformAction performAction = new PerformAction();
    Personeel personeel = new Personeel();


    @Test(groups = "employee-management")
    public void testNavigateToPersoneelMenu() throws InterruptedException {
        performAction.tabNavigation(5);
    }

    @Test(groups = "employee-management", dependsOnMethods = "testNavigateToPersoneelMenu")
    public void testCreateEmployee() throws InterruptedException {
        performAction.clickElement(By.tagName("button"));

        // Fill employee details
        performAction.typeField(By.id("Voornaam"), "Scarlett");
        performAction.typeField(By.id("Tussenvoegsel"), "Faith");
        performAction.typeField(By.id("Achternaam"), "Turner");

        List<WebElement> dropDown = performAction.getDropDown();

        //Select Employee Type
        dropDown.get(0).click();
        performAction.clickElement(By.xpath("//*[@role='option'][1]"));

        //Select Gender
        dropDown.get(1).click();
        performAction.clickElement(By.xpath("//*[@role='option'][2]"));

        performAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());
        performAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        performAction.typeField(By.id("E-mailadres"), RandomInput.email());

        // Nationality
        performAction.clickElement(By.xpath("//app-autocomplete-field/div/input"));
        performAction.clickElement(By.id("mat-option-12"));

        // Company details
        performAction.typeField(By.id("KVK"), "34148704");
        performAction.typeField(By.id("BTW-nummer"), "865845621B01");
        performAction.typeField(By.id("Rekeningnummer"), "02ABNA0123456789");
        performAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        // Checkbox and submit
        performAction.clickElement(By.xpath("//input[@type='checkbox']"));
        performAction.clickElement(By.xpath("//button[@type='submit']"));

        PerformAction.longWait();
    }

    @Test(groups = "employee-management", dependsOnMethods = "testCreateEmployee")
    public void testSearchEmployee() throws InterruptedException {
        personeel.searchAndShowEmployee("Scarlett");
    }

    @Test(groups = "employee-management", dependsOnMethods = "testSearchEmployee")
    public void testModifyEmployee() throws InterruptedException {
        personeel.openEmployeeMenu(1);

        // Modify details
        performAction.typeField(By.id("Voornaam"), "Dylan");
        performAction.typeField(By.id("Tussenvoegsel"), "Nicholas");
        performAction.typeField(By.id("Achternaam"), "Reed");

        List<WebElement> dropDown = performAction.getDropDown();

        //Change Employee Type
        dropDown.get(0).click();
        performAction.clickElement(By.xpath("//*[@role='option'][2]"));

        //Change Gender
        dropDown.get(1).click();
        performAction.clickElement(By.xpath("//*[@role='option'][1]"));

        performAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());
        performAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        performAction.typeField(By.id("E-mailadres"), RandomInput.email());

        // Change nationality
        performAction.typeField(By.xpath("//app-autocomplete-field/div/input"), "Bengaals");

        // Company details
        performAction.typeField(By.id("KVK"), "53531795");
        performAction.typeField(By.id("Fee per uur"), RandomInput.threeDigit());

        // Submit changes
        performAction.clickElement(By.xpath("//div[4]/submit-button/button"));
        PerformAction.longWait();
    }
}
