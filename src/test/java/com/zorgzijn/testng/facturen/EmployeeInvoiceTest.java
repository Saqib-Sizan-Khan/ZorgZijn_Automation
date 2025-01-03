package com.zorgzijn.testng.facturen;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EmployeeInvoiceTest extends ZorgzijnBaseTest {

    PerformAction performAction = new PerformAction();


    @Test(groups = "shift-invoice")
    public void testNavigateToInvoiceMenu() throws InterruptedException {
        performAction.tabNavigation(6);
        PerformAction.longWait();
    }

    @Test(groups = "shift-invoice", dependsOnMethods = "testNavigateToInvoiceMenu")
    public void testInvoiceShift() throws InterruptedException {


        //Checked all shifts
        performAction.clickElement(By.xpath("//tr/th[1]/mat-checkbox"));

        // Confirm invoice
        performAction.clickElement(By.xpath("//submit-button[2]/button"));

        // Check invoice status
        performAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        performAction.clickElement(By.xpath("//mat-option[2]"));

        performAction.veryLongWait();
        performAction.veryLongWait();

        performAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        performAction.clickElement(By.xpath("//mat-option[4]"));

        performAction.longWait();

        performAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        performAction.clickElement(By.xpath("//mat-option[3]"));

        performAction.longWait();
    }
}
