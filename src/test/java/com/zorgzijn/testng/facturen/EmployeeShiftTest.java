package com.zorgzijn.testng.facturen;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

public class EmployeeShiftTest extends ZorgzijnBaseTest {

    int location = 1;
    int column = 5;
    int startRow = 7;
    int endRow = 10;

    Random random = new Random();

    @Test(groups = "shift-management")
    public void testNavigateToPlanningMenu() throws InterruptedException {
        PerformAction.tabNavigation(2);
        PerformAction.longWait();
    }

    @Test(groups = "shift-management", dependsOnMethods = "testNavigateToPlanningMenu")
    public void testCreateShifts() throws InterruptedException {
        for (int i = startRow; i < endRow +1; i++) {
            // Open shift creation form
            PerformAction.clickElement(By.xpath("//tbody/div["+ location +"]/tr["+ i +"]/td["+ column +"]"));
            PerformAction.shortWait();

            //Choose a shift
            PerformAction.clickElement(By.xpath("//form//mat-select"));
            int totalService = PerformAction.countOption(By.xpath("//mat-option"));
            int pickService = 1 + random.nextInt(totalService);
            PerformAction.clickElement(By.xpath("//mat-option[" + pickService + "]"));

            //Type description
            PerformAction.typeField(By.tagName("textarea"), RandomInput.text());

            // Submit form to create the shift
            PerformAction.clickElement(By.xpath("//form/div/button[1]"));
            PerformAction.longWait();
        }
    }

    @Test(groups = "shift-management", dependsOnMethods = "testCreateShifts")
    public void testModifyShift() throws InterruptedException {

        int randomRow = startRow + random.nextInt((endRow - startRow) + 1);

        //Open the modify shift form
        PerformAction.clickElement(By.xpath("//tbody/div["+ location +"]/tr["+ randomRow +"]/td["+ column +"]//button"));
        PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content button:nth-of-type(1)"));

        //Modify shift info
        PerformAction.typeField(By.id("Titel"),"Modified Automated Service");
        PerformAction.typeField(By.tagName("textarea"),RandomInput.text());
        PerformAction.clickElement(By.xpath("//color-picker/div/div["+ randomRow +"]"));
        PerformAction.inputTime(By.id("Starttijd"),"11:22");
        PerformAction.inputTime(By.id("Eindtijd"),"8:47");
        PerformAction.typeField(By.id("Pauze"),"23");

        // Submit changes
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.shortWait();
    }

    @Test(groups = "shift-management", dependsOnMethods = "testModifyShift")
    public void testDeleteShift() throws InterruptedException {

        int randomRow = startRow + random.nextInt((endRow - startRow) + 1);

        //Open the delete confirmation dialog
        PerformAction.clickElement(By.xpath("//tbody/div["+ location +"]/tr["+ randomRow +"]/td["+ column +"]//button"));
        PerformAction.clickElement(By.cssSelector(".mat-mdc-menu-content button:nth-of-type(2)"));

        // Confirm Delete
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }
}
