package com.zorgzijn.testng.setup;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class ZorgzijnBaseTest {

    public WebDriver driver;
    public String baseUrl;
    public String username;
    public String password;

    @Parameters({"baseUrl", "email", "password"})
    @BeforeSuite(alwaysRun = true)
    public void setup(String baseUrl, String email, String password) throws InterruptedException {
        this.baseUrl = baseUrl;
        this.username = email;
        this.password = password;
        driver = WebDriverManager.getDriver();
        if (driver == null) {
            throw new RuntimeException("Driver initialization failed. Ensure WebDriver is set up properly.");
        }
        System.out.println("Driver initialized successfully.");
        System.out.println(driver.getCurrentUrl());
    }

    @AfterSuite(alwaysRun = true)
    public void terminate() {
        WebDriverManager.quitDriver();
        System.out.println("Driver terminated.");
    }
}
