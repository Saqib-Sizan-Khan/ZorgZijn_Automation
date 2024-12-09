package com.zorgzijn.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NotificationAutomation {
    WebDriver driver;
    String baseUrl = "https://zorgzijn-dev.acegreen.nl";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
        driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
        driver.findElement(By.id("remember")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToNotificationTab() throws InterruptedException {
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//li[2]/a")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToNotificationTab")
    public void createNotification() throws InterruptedException {
        // Open notification creation form
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

        // 1st step
        driver.findElement(By.id("Titel")).sendKeys("Automation Notification");
        driver.findElement(By.xpath("//div[@class='ck ck-editor__main']/div"))
                .sendKeys("This is an automation text");

        // Upload a file (if required, uncomment)
        // WebElement fileInput = driver.findElement(By.xpath("//label[@class='relative']"));
        // ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);
        // fileInput.sendKeys("C:\\Users\\ssk12\\Downloads\\lorem_Ipsum.pdf");
        Thread.sleep(2000);

        // Click Next
        driver.findElement(By.xpath("//form/div/button[1]")).click();
        Thread.sleep(2000);

        // 2nd step Choose employee
        driver.findElement(By.id("simple-search")).sendKeys("Sizan");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//li[2]//input")).click();
        Thread.sleep(1000);

        // Confirm notification
        driver.findElement(By.xpath("//submit-button/button")).click();
        Thread.sleep(3000);

    }

    @Test(priority = 4, dependsOnMethods = "createNotification")
    public void viewNotificationDetails() throws InterruptedException {
        // See notification details (Scenario 1)
        driver.findElement(By.xpath("//div[2]/div[2]/div[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(3000);

        // See notification details (Scenario 2)
        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5, dependsOnMethods = "viewNotificationDetails")
    public void deleteNotification() throws InterruptedException {
        // Delete notification
        driver.findElement(By.xpath("//div[2]/div[1]/div[4]/button/span[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void terminate() {
        driver.quit();
    }
}
