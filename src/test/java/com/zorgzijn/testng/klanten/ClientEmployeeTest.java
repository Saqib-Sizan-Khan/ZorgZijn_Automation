package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class ClientEmployeeTest extends ZorgzijnBaseTest {

    int empCount;
    Random random = new Random();

    @Test(groups = "client-employee-management")
    public void testNavigateToKlantenMenu() throws InterruptedException {
        PerformAction.tabNavigation(4);
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testNavigateToKlantenMenu")
    public void testSearchClient() throws InterruptedException {
        Klanten.searchAndShowClient(Klanten.getClientName());
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testSearchClient")
    public void testNavigateToPersoneelTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//client-tabs/div/div[2]"));
        PerformAction.shortWait();
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testNavigateToPersoneelTab")
    public void testAddEmployees() throws InterruptedException {
        PerformAction.shortWait();
        PerformAction.clickElement(By.xpath("//client-employees/div/div[1]//button"));
        int totalLocation = PerformAction.countOption(By.xpath("//client-employees//ul/li"));
        int pickLocation = 1 + random.nextInt(totalLocation);

        if (pickLocation == 1) {
            pickLocation++;
        }

        PerformAction.clickElement(By.xpath("//client-employees//ul/li[" + pickLocation + "]"));

        empCount = 2 + random.nextInt(2);
        for (int i = 0; i < empCount; i++) {
            PerformAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.longWait();

            //Select Employee
            PerformAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
            int totalEmp = PerformAction.countOption(By.xpath("//mat-option"));
            int pickEmp = 1 + random.nextInt(totalEmp);
            PerformAction.clickElement(By.xpath("//mat-option[" + pickEmp + "]"));

            //Set Employee fee
            PerformAction.typeField(By.id("Doordeweekse uren"), RandomInput.threeDigit());
            PerformAction.typeField(By.id("Weekenduren"), RandomInput.threeDigit());

            //Set Contract type
            PerformAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
            PerformAction.clickElement(By.xpath("//mat-option[1]"));

            //Set Profile type
            PerformAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
            int totalProfiles = PerformAction.countOption(By.xpath("//mat-option"));
            int pickProfile = 1 + random.nextInt(totalProfiles);
            PerformAction.clickElement(By.xpath("//mat-option[" + pickProfile + "]"));

            //Click submit
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testAddEmployees")
    public void testModifyEmployees() throws InterruptedException {
        empCount = PerformAction.countOption(By.xpath("//client-employee-info"));

        for (int i = 0; i < 2; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Activate employee modification
            PerformAction.clickElement(By.xpath("//div[" + employee + "]/client-employee-info//div[2]/button[2]"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change Employee fee
            PerformAction.typeField(By.xpath("//div[1]/app-rate-input-field//input"), RandomInput.threeDigit());
            PerformAction.typeField(By.xpath("//div[2]/app-rate-input-field//input"), RandomInput.threeDigit());

            //Submit Changes
            PerformAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testModifyEmployees")
    void testDeleteEmployees() throws InterruptedException {
        empCount = PerformAction.countOption(By.xpath("//client-employee-info"));

        for (int i = 0; i < 1; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Open employee delete dialog
            PerformAction.clickElement(By.xpath("//div[" + employee + "]/client-employee-info//div[2]/button[2]"));
            PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
            empCount -= 1;
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testDeleteEmployees")
    public void testDeleteClient() throws InterruptedException {
        Klanten.openClientMenu(3);
        PerformAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
        PerformAction.longWait();
    }
}
