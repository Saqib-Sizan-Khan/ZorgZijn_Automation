package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginAutomation extends AutomationSetupClass {
    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
        driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
        driver.findElement(By.id("remember")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
    }
}
