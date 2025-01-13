package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import lombok.Getter;
import org.openqa.selenium.By;

public class Role {

    @Getter
    private static String roleName = "Automation Role";

    public static void setRoleName(String roleName) {
        Role.roleName = roleName;
    }

    public static void selectRole(int roleNum) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li["+ roleNum +"]/div/input"));
    }

    public static void openRoleMenu(int menuItem) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//div[2]/div/button"));
        PerformAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type("+ menuItem +")"));
    }
}
