package com.zorgzijn.testng.personeel;

import com.zorgzijn.testng.setup.PerformAction;
import org.openqa.selenium.By;

public class Personeel {

    public static void searchAndShowEmployee(String employeeName) throws InterruptedException {
        PerformAction.typeField(By.id("simple-search"), employeeName);
        PerformAction.shortWait();
        PerformAction.clickElement(By.xpath("//staff-list/div/div[1]"));
        PerformAction.longWait();
    }

    public static void openEmployeeMenu(int menuItem) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//staff-details/div/staff-header/div/div[2]/button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem']["+ menuItem +"]"));
    }
}
