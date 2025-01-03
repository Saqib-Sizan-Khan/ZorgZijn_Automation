package com.zorgzijn.testng.personeel;

import com.zorgzijn.testng.setup.PerformAction;
import org.openqa.selenium.By;

public class Personeel {

    PerformAction performAction = new PerformAction();

    public void searchAndShowEmployee(String employeeName) throws InterruptedException {
        performAction.typeField(By.id("simple-search"), employeeName);
        PerformAction.shortWait();
        performAction.clickElement(By.xpath("//staff-list/div/div[1]"));
        PerformAction.longWait();
    }

    public void openEmployeeMenu(int menuItem) throws InterruptedException {
        performAction.clickElement(By.xpath("//staff-details/div/staff-header/div/div[2]/button"));
        performAction.clickElement(By.xpath("//button[@role='menuitem']["+ menuItem +"]"));
    }
}
