package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import lombok.Getter;
import org.openqa.selenium.By;

public class User {

    @Getter
    private static String userFirstName = "Selenium";
    @Getter
    private static String userMiddleName = "Automated";
    @Getter
    private static String userLastName = "User";

    public static void setFirstName(String firstName) {
        User.userFirstName = firstName;
    }

    public static void setMiddleName(String middleName) {
        User.userMiddleName = middleName;
    }

    public static void setLastName(String lastName) {
        User.userLastName = lastName;
    }

    public static void selectRole(int roleNum) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li["+ roleNum +"]/div/input"));
    }

    public static void openUserMenu(int menuItem) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//tr[1]/td[4]/button"));
        PerformAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type("+ menuItem +")"));
    }
}
