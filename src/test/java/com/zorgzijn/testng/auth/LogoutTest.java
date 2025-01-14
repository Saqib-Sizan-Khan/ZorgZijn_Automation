package com.zorgzijn.testng.auth;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LogoutTest extends ZorgzijnBaseTest {

    private final By logoutButton = By.xpath("//div[2]/div/button");

    @Test(groups = "logout")
    void testLogin() throws InterruptedException {
        System.out.println("Performing logout...");

        PerformAction.tabNavigation(7);
        PerformAction.longWait();
        PerformAction.clickElement(logoutButton);
        PerformAction.longWait();
        System.out.println("Logout successful.");
    }
}
