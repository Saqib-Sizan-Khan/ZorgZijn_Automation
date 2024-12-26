package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class PersoneelAutomation extends AutomationSetupClass {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        PerformAction.waitForPageLoad();

        PerformAction.sendKeysToElement(By.id("E-mailadres"), "ssk123098@gmail.com");
        PerformAction.sendKeysToElement(By.id("Wachtwoord"), "Sizan@1999");
        PerformAction.clickElement(By.id("remember"));

        // Submit the login form
        PerformAction.clickElement(By.xpath("//button"));
        PerformAction.waitForPageLoad();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPersoneelTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//div/a[5]"));
        Thread.sleep(3000);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPersoneelTab")
    public void createEmployee() throws InterruptedException {

        PerformAction.clickElement(By.tagName("button"));

        //Type Employee Name
        PerformAction.sendKeysToElement(By.id("Voornaam"),"Scarlett");
        PerformAction.sendKeysToElement(By.id("Tussenvoegsel"),"Faith");
        PerformAction.sendKeysToElement(By.id("Achternaam"),"Turner");

        List<WebElement> dropDown = driver.findElements(By.tagName("mat-select"));

        //Select Employee Type
        dropDown.get(0).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][1]"));

        //Select Gender
        dropDown.get(1).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][2]"));

        //Type Phone, DOB and Email
        PerformAction.sendKeysToElement(By.id("Geboortedatum"), RandomInput.birthday());
        PerformAction.sendKeysToElement(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.sendKeysToElement(By.id("E-mailadres"), RandomInput.email());

        //Select Nationality
        PerformAction.clickElement(By.xpath("//app-autocomplete-field/div/input"));
        PerformAction.clickElement(By.id("mat-option-12"));

        //Type Company Details
        PerformAction.sendKeysToElement(By.id("KVK"), "34148704");
        PerformAction.sendKeysToElement(By.id("BTW-nummer"), "865845621B01");
        PerformAction.sendKeysToElement(By.id("Rekeningnummer"), "02ABNA0123456789");
        PerformAction.sendKeysToElement(By.id("Fee per uur"), RandomInput.feePerHour());

        //Checked relationship code
        PerformAction.clickElement(By.xpath("//input[@type='checkbox']"));

        //Submit form
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));

        PerformAction.longWait();
    }

    @Test(priority = 4, dependsOnMethods = "createEmployee")
    public void searchEmployee() throws InterruptedException {
        PerformAction.sendKeysToElement(By.id("simple-search"), "Scarlett");
        PerformAction.shortWait();
        PerformAction.clickElement(By.xpath("//staff-list/div/div[1]"));
        PerformAction.shortWait();
    }

    @Test(priority = 5, dependsOnMethods = "searchEmployee")
    public void modifyEmployee() throws InterruptedException {

        PerformAction.clickElement(By.xpath("//staff-details/div/staff-header/div/div[2]/button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem'][1]"));

        //Change Employee Name
        PerformAction.sendKeysToElement(By.id("Voornaam"),"Dylan");
        PerformAction.sendKeysToElement(By.id("Tussenvoegsel"),"Nicholas");
        PerformAction.sendKeysToElement(By.id("Achternaam"),"Reed");

        List<WebElement> dropDown = driver.findElements(By.tagName("mat-select"));

        //Change Employee Type
        dropDown.get(0).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][2]"));

        //Change Gender
        dropDown.get(1).click();
        PerformAction.clickElement(By.xpath("//*[@role='option'][1]"));

        //Change Phone, DOB and Email
        PerformAction.sendKeysToElement(By.id("Geboortedatum"), RandomInput.birthday());
        PerformAction.sendKeysToElement(By.id("Telefoonnummer"), RandomInput.phoneNumber());
        PerformAction.sendKeysToElement(By.id("E-mailadres"), RandomInput.email());

        //Change Nationality
        PerformAction.sendKeysToElement(By.xpath("//app-autocomplete-field/div/input"), "Bengaals");

        //Change Company Details
        PerformAction.sendKeysToElement(By.id("KVK"), "53531795");
        PerformAction.sendKeysToElement(By.id("Fee per uur"), RandomInput.feePerHour());

        //Submit form
        PerformAction.clickElement(By.xpath("//div[4]/submit-button/button"));

        PerformAction.longWait();
    }

    @Test(priority = 6, dependsOnMethods = "modifyEmployee")
    public void changeEmployeeFilter() throws InterruptedException {
        PerformAction.clearField(By.id("simple-search"));
        PerformAction.clickElement(By.xpath("//div[3]/button"));
        PerformAction.clickElement(By.id("mat-radio-4-input"));
        PerformAction.longWait();
    }

    @Test(priority = 7, dependsOnMethods = "changeEmployeeFilter")
    public void searchEmployeeAgain() throws InterruptedException {
        PerformAction.sendKeysToElement(By.id("simple-search"), "Dylan");
        PerformAction.shortWait();
        PerformAction.clickElement(By.xpath("//staff-list/div/div[1]"));
        PerformAction.shortWait();
    }

    @Test(priority = 8, dependsOnMethods = "searchEmployeeAgain")
    public void deleteEmployee() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//staff-details/div/staff-header/div/div[2]/button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem'][3]"));
        PerformAction.clickElement(By.xpath("//app-delete-dialog//div[3]/button[1]"));
        PerformAction.shortWait();
    }
}
