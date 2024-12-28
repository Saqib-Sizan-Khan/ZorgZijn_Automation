package com.zorgzijn.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class AutomationSetupClass {
    static WebDriver driver;
    String baseUrl = "https://zorgzijn-dev.acegreen.nl";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void baseLogin() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        PerformAction.waitForPageLoad();

        PerformAction.typeField(By.id("E-mailadres"), "ssk123098@gmail.com");
        PerformAction.typeField(By.id("Wachtwoord"), "Sizan@1999");
        PerformAction.clickElement(By.id("remember"));

        // Submit the login form
        PerformAction.clickElement(By.xpath("//button"));
        PerformAction.waitForPageLoad();
    }

    public void tabNavigation(int tabNum) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//div/a["+ tabNum +"]"));
        PerformAction.shortWait();
    }

    @AfterTest
    public void terminate() {
        driver.quit();
    }
}
