package com.zorgzijn.testng.facturen;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegisterShiftTest extends ZorgzijnBaseTest {

    @Test(groups = "shift-register")
    public void testNavigateToTimeRegistrationMenu() throws InterruptedException {
        tabNavigation(3);
        PerformAction.longWait();
    }

    @Test(groups = "shift-register", dependsOnMethods = "testNavigateToTimeRegistrationMenu")
    public void testRegisterShift() throws InterruptedException {
        int empCount = driver.findElements(By.xpath("//table/tbody/tr")).size();

        for (int i=1; i<=empCount; i++) {
            try {
                PerformAction.clickElement(By.xpath("//table/tbody/tr["+ i +"]/td[10]/div/button[2]"));
            } catch (Exception e) {
                System.out.println("Already service definative");
            }
            PerformAction.shortWait();
        }
    }
}
