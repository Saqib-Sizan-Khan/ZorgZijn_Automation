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

    PerformAction performAction = new PerformAction();
    Klanten klanten = new Klanten();


    @Test(groups = "client-employee-management")
    public void testNavigateToKlantenMenu() throws InterruptedException {
        performAction.tabNavigation(4);
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testNavigateToKlantenMenu")
    public void testSearchClient() throws InterruptedException {
        klanten.searchAndShowClient(Klanten.getClientName());
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testSearchClient")
    public void testNavigateToPersoneelTab() throws InterruptedException {
        performAction.clickElement(By.xpath("//client-tabs/div/div[2]"));
        PerformAction.shortWait();
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testNavigateToPersoneelTab")
    public void testAddEmployees() throws InterruptedException {
        PerformAction.shortWait();
        performAction.clickElement(By.xpath("//client-employees/div/div[1]//button"));
        int totalLocation = driver.findElements(By.xpath("//client-employees//ul/li")).size();
        int pickLocation = 1 + random.nextInt(totalLocation);

        if (pickLocation == 1) {
            pickLocation++;
        }

        performAction.clickElement(By.xpath("//client-employees//ul/li[" + pickLocation + "]"));

        empCount = 2 + random.nextInt(2);
        for (int i = 0; i < empCount; i++) {
            performAction.clickElement(By.xpath("//button[@type='submit']"));
            PerformAction.shortWait();

            //Select Employee
            performAction.clickElement(By.xpath("//div[1]/app-autocomplete-field//input"));
            int totalEmp = driver.findElements(By.xpath("//mat-option")).size();
            int pickEmp = 1 + random.nextInt(totalEmp);
            performAction.clickElement(By.xpath("//mat-option[" + pickEmp + "]"));

            //Set Employee fee
            performAction.typeField(By.id("Doordeweekse uren"), RandomInput.threeDigit());
            performAction.typeField(By.id("Weekenduren"), RandomInput.threeDigit());

            //Set Contract type
            performAction.clickElement(By.xpath("//div[2]/app-select-field/div/mat-select"));
            performAction.clickElement(By.xpath("//mat-option[1]"));

            //Set Profile type
            performAction.clickElement(By.xpath("//div[2]/app-autocomplete-field//input"));
            int totalProfiles = driver.findElements(By.xpath("//mat-option")).size();
            int pickProfile = 1 + random.nextInt(totalProfiles);
            performAction.clickElement(By.xpath("//mat-option[" + pickProfile + "]"));

            //Click submit
            performAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testAddEmployees")
    public void testModifyEmployees() throws InterruptedException {
        empCount = driver.findElements(By.xpath("//client-employee-info")).size();

        for (int i = 0; i < 2; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Activate employee modification
            performAction.clickElement(By.xpath("//div[" + employee + "]/client-employee-info//div[2]/button[2]"));
            performAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(1)"));

            //Change Employee fee
            performAction.typeField(By.xpath("//div[1]/app-rate-input-field//input"), RandomInput.threeDigit());
            performAction.typeField(By.xpath("//div[2]/app-rate-input-field//input"), RandomInput.threeDigit());

            //Submit Changes
            performAction.clickElement(By.xpath("//submit-button/button"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testModifyEmployees")
    void testDeleteEmployees() throws InterruptedException {
        empCount = driver.findElements(By.xpath("//client-employee-info")).size();

        for (int i = 0; i < 1; i++) {
            int employee = 2 + random.nextInt(empCount);

            //Open employee delete dialog
            performAction.clickElement(By.xpath("//div[" + employee + "]/client-employee-info//div[2]/button[2]"));
            performAction.clickElement(By.cssSelector(".mat-mdc-menu-content > button:nth-child(2)"));

            //Confirm delete
            performAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
            empCount -= 1;
            PerformAction.longWait();
        }
    }

    @Test(groups = "client-employee-management", dependsOnMethods = "testDeleteEmployees")
    public void testDeleteClient() throws InterruptedException {
        klanten.openClientMenu(3);
        performAction.clickElement(By.xpath("//delete-dialog//div[3]/button[1]"));
        PerformAction.longWait();
    }
}
