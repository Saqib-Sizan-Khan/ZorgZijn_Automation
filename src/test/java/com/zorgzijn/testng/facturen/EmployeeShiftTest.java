package com.zorgzijn.testng.facturen;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class EmployeeShiftTest extends ZorgzijnBaseTest {

    int location = 1;
    int tableColumnPosition = 6;
    Random random = new Random();

    @Test(groups = "create-shift")
    public void testNavigateToPlanningMenu() throws InterruptedException {
        PerformAction.tabNavigation(2);
        PerformAction.longWait();
    }

    @Test(groups = "create-shift", dependsOnMethods = "testNavigateToPlanningMenu")
    public void testCreateShifts() throws InterruptedException {
        for (int i=6; i<10; i++) {
            // Open service creation form
            PerformAction.clickElement(By.xpath("//tbody/div["+ location +"]/tr["+ i +"]/td["+ tableColumnPosition +"]"));
            PerformAction.shortWait();

            //Choose a service
            PerformAction.clickElement(By.xpath("//form//mat-select"));
            int totalService = driver.findElements(By.xpath("//mat-option")).size();
            int pickService = 1 + random.nextInt(totalService);
            PerformAction.clickElement(By.xpath("//mat-option[" + pickService + "]"));

            //Type description
            PerformAction.typeField(By.tagName("textarea"), RandomInput.text());

            // Submit form to create the service
            PerformAction.clickElement(By.xpath("//form/div/button[1]"));
            PerformAction.longWait();
        }
    }
}
