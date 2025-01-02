package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.utils.PerformAction;
import com.zorgzijn.testng.utils.AutomationSetupClass;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class EmpClient extends AutomationSetupClass {

    int empCount;
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
        Klanten.searchAndShowClient("Henderik Hospital Waste");
    }

    @Test(priority = 4, dependsOnMethods = "searchClient")
    public void navigateToPersoneelOption() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//client-tabs/div/div[2]"));
        PerformAction.shortWait();
    }

    @Test(priority = 5, dependsOnMethods = "navigateToPersoneelOption")
    public void addEmployees() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//client-employees/div/div[1]//button"));
        int totalLocation = driver.findElements(By.xpath("//client-employees//ul/li")).size();
        int pickLocation = 1 + random.nextInt(totalLocation);

        if (pickLocation == 1) {pickLocation++;}

        PerformAction.clickElement(By.xpath("//client-employees//ul/li["+ pickLocation +"]"));

        empCount = 3 + random.nextInt(4);
        for (int i=0; i<empCount; i++) {
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.shortWait();

            //Select Employee
            PerformAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
            int totalEmp = driver.findElements(By.xpath("//mat-option")).size();
            int pickEmp = 1 + random.nextInt(totalEmp);
            PerformAction.clickElement(By.xpath("//mat-option["+ pickEmp +"]"));

            //Set Employee fee
            PerformAction.typeField(By.id("Doordeweekse uren"), RandomInput.threeDigit());
            PerformAction.typeField(By.id("Weekenduren"), RandomInput.threeDigit());

            //Set Contract type
            PerformAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
            PerformAction.clickElement(By.xpath("//mat-option[1]"));

            //Set Profile type
            PerformAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
            int totalProfiles = driver.findElements(By.xpath("//mat-option")).size();
            int pickProfile = 1 + random.nextInt(totalProfiles);
            PerformAction.clickElement(By.xpath("//mat-option["+ pickProfile +"]"));

            //Click submit
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(priority = 6, dependsOnMethods = "addEmployees")
    public void modifyEmployees() throws InterruptedException {
        empCount = driver.findElements(By.xpath("//client-employee-info")).size();

        for (int i = 0; i < 3; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Activate employee modification
            PerformAction.clickElement(By.xpath("//div["+ employee +"]/client-employee-info//div[2]/button[2]"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change Employee fee
            PerformAction.typeField(By.xpath("//div[1]/app-rate-input-field//input"), RandomInput.threeDigit());
            PerformAction.typeField(By.xpath("//div[2]/app-rate-input-field//input"), RandomInput.threeDigit());

            //Submit Changes
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(priority = 7, dependsOnMethods = "modifyEmployees")
    public void deleteEmployees() throws InterruptedException {
        empCount = driver.findElements(By.xpath("//client-employee-info")).size();

        for (int i = 0; i < 2; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Open employee delete dialog
            PerformAction.clickElement(By.xpath("//div["+ employee +"]/client-employee-info//div[2]/button[2]"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
            empCount-=1;
            PerformAction.longWait();
        }
    }

    @Test(priority = 8, dependsOnMethods = "deleteEmployees")
    public void deleteEmployee() throws InterruptedException {
        Klanten.openClientMenu(3);
        PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
        PerformAction.longWait();
    }
}
