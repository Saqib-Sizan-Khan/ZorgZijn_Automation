package com.zorgzijn.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NotificationCRUD_Operation {
    WebDriver driver;
    String baseUrl = "https://zorgzijn-dev.acegreen.nl";

    @BeforeTest
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void notificationCRUD() throws Exception {
        //login automation
        driver.get(baseUrl + "/auth/login");
        Thread.sleep(2000);

        driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
        driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
        driver.findElement(By.id("remember")).click();
        Thread.sleep(2000);

        //Navigate to notification tab
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//li[2]/a")).click();
        Thread.sleep(2000);

        //open notification creation form
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);

        //Fill up 1st step
        driver.findElement(By.id("Titel")).sendKeys("Automation Notification");
        driver.findElement(By.xpath("//div[@class='ck ck-editor__main']/div")).sendKeys("This is a automation text");

//        WebElement fileInput = driver.findElement(By.xpath("//label[@class='relative']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);
//        fileInput.sendKeys("C:\\Users\\ssk12\\Downloads\\lorem_Ipsum.pdf");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//form/div/button[1]")).click();
        Thread.sleep(2000);

        //Choose Employee
        driver.findElement(By.id("simple-search")).sendKeys("Sizan");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//li[2]//input")).click();
        Thread.sleep(1000);

        //Confirm notification
        driver.findElement(By.xpath("//submit-button/button")).click();
        Thread.sleep(2000);

        //See notification details
        driver.findElement(By.xpath("//div[2]/div[2]/div[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void after() {
        driver.quit();
    }
}
