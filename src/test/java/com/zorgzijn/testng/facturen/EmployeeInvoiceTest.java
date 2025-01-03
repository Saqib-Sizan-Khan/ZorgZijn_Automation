package com.zorgzijn.testng.facturen;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EmployeeInvoiceTest extends ZorgzijnBaseTest {

    @Test(groups = "shift-invoice")
    public void testNavigateToInvoiceMenu() throws InterruptedException {
        PerformAction.tabNavigation(6);
        PerformAction.longWait();
    }

    @Test(groups = "shift-invoice", dependsOnMethods = "testNavigateToInvoiceMenu")
    public void testInvoiceShift() throws InterruptedException {

        //Checked all shifts
        PerformAction.clickElement(By.xpath("//tr/th[1]/mat-checkbox"));

        // Confirm invoice
        PerformAction.clickElement(By.xpath("//submit-button[2]/button"));

        // Check invoice status
        PerformAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[2]"));

        PerformAction.veryLongWait();
        PerformAction.veryLongWait();

        PerformAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[4]"));

        PerformAction.longWait();

        PerformAction.clickElement(By.xpath("//app-select-field/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[3]"));

        PerformAction.longWait();
    }
}
