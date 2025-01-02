package com.zorgzijn.testng.auth;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends ZorgzijnBaseTest {

    private final By emailField = By.id("E-mailadres");
    private final By passwordField = By.id("Wachtwoord");
    private final By rememberMeCheckbox = By.id("remember");
    private final By loginButton = By.xpath("//button");

    @Test(groups = "login", priority = 1)
    public void loginTest() throws InterruptedException {
        System.out.println("Performing login...");
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Ensure setup is executed properly.");
        }
        driver.get(baseUrl + "/auth/login");
        PerformAction.waitForPageLoad();
        PerformAction.typeField(emailField, username);
        PerformAction.typeField(passwordField, password);
        PerformAction.clickElement(rememberMeCheckbox);
        PerformAction.clickElement(loginButton);
        PerformAction.waitForPageLoad();
        System.out.println("Login successful.");
    }
}
