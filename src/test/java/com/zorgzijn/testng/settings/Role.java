package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import lombok.Getter;
import lombok.Setter;
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

    public static void openClientMenu(int menuItem) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//client-details/div/client-header/div/div[2]/button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem']["+ menuItem +"]"));
    }
}
