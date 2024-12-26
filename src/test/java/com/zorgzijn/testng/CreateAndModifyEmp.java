package com.zorgzijn.testng;

import com.zorgzijn.testng.utils.Personeel;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CreateAndModifyEmp extends AutomationSetupClass {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        baseLogin();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPersoneelTab() throws InterruptedException {
        tabNavigation(5);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPersoneelTab")
    public void createEmployee() throws InterruptedException {
        PerformAction.clickElement(By.tagName("button"));

        // Fill employee details
        PerformAction.typeField(By.id("Voornaam"), "Scarlett");
        PerformAction.typeField(By.id("Tussenvoegsel"), "Faith");
        PerformAction.typeField(By.id("Achternaam"), "Turner");

        List<WebElement> dropDown = driver.findElements(By.tagName("mat-select"));

        //Select Employee Type
        dropDown.get(0).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][1]"));

        //Select Gender
        dropDown.get(1).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][2]"));

        PerformAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());
        PerformAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.typeField(By.id("E-mailadres"), RandomInput.email());

        // Nationality
        PerformAction.clickElement(By.xpath("//app-autocomplete-field/div/input"));
        PerformAction.clickElement(By.id("mat-option-12"));

        // Company details
        PerformAction.typeField(By.id("KVK"), "34148704");
        PerformAction.typeField(By.id("BTW-nummer"), "865845621B01");
        PerformAction.typeField(By.id("Rekeningnummer"), "02ABNA0123456789");
        PerformAction.typeField(By.id("Fee per uur"), RandomInput.feePerHour());

        // Checkbox and submit
        PerformAction.clickElement(By.xpath("//input[@type='checkbox']"));
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));

        PerformAction.longWait();
    }

    @Test(priority = 4, dependsOnMethods = "createEmployee")
    public void searchEmployee() throws InterruptedException {
        Personeel.searchAndShowEmployee("Scarlett");
    }

    @Test(priority = 5, dependsOnMethods = "searchEmployee")
    public void modifyEmployee() throws InterruptedException {
        Personeel.openEmployeeMenu(1);

        // Modify details
        PerformAction.typeField(By.id("Voornaam"), "Dylan");
        PerformAction.typeField(By.id("Tussenvoegsel"), "Nicholas");
        PerformAction.typeField(By.id("Achternaam"), "Reed");

        List<WebElement> dropDown = driver.findElements(By.tagName("mat-select"));

        //Change Employee Type
        dropDown.get(0).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][2]"));

        //Change Gender
        dropDown.get(1).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][1]"));

        PerformAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());
        PerformAction.typeField(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.typeField(By.id("E-mailadres"), RandomInput.email());

        // Change nationality
        PerformAction.typeField(By.xpath("//app-autocomplete-field/div/input"), "Bengaals");

        // Company details
        PerformAction.typeField(By.id("KVK"), "53531795");
        PerformAction.typeField(By.id("Fee per uur"), RandomInput.feePerHour());

        // Submit changes
        PerformAction.clickElement(By.xpath("//div[4]/submit-button/button"));
        PerformAction.longWait();
    }
}
