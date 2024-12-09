package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KlantCreation {

  WebDriver driver;
  String baseUrl = "https://zorgzijn-dev.acegreen.nl";
	
	@BeforeTest
	public void before() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

  @Test
  public void LoginTestCase() throws Exception {
    driver.get(baseUrl+"/auth/login");
    
    Thread.sleep(2000);
    
    driver.findElement(By.id("E-mailadres")).click();
    driver.findElement(By.id("E-mailadres")).sendKeys("ssk123098@gmail.com");
    
    driver.findElement(By.id("Wachtwoord")).click();
    driver.findElement(By.id("Wachtwoord")).sendKeys("Sizan@1999");
    
    driver.findElement(By.id("remember")).click();
    Thread.sleep(1000);
    
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    
    driver.findElement(By.linkText("Klanten")).click();
    
    driver.findElement(By.tagName("button")).click();
    
    Thread.sleep(2000);
    
    driver.findElement(By.id("KVK")).click();
    driver.findElement(By.id("KVK")).sendKeys("74748955");
    driver.findElement(By.id("Telefoonnummer")).click();
    driver.findElement(By.id("Telefoonnummer")).sendKeys("+31241365439");
    
    Thread.sleep(2000);
    
    driver.findElement(By.id("E-mailadres")).click();
    driver.findElement(By.id("E-mailadres")).sendKeys("sizan.sqa@gmail.com");
    
    Thread.sleep(2000);
    
    driver.findElement(By.id("BTW-nummer")).click();
    driver.findElement(By.id("BTW-nummer")).sendKeys("865845621B01");
    driver.findElement(By.id("Rekeningnummer")).click();
    driver.findElement(By.id("Rekeningnummer")).sendKeys("02ABNA0123456789");
    
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[@id='mat-select-value-1']/span")).click();
    
    Thread.sleep(1000);
    driver.findElement(By.id("mat-option-0")).click();
    
    Thread.sleep(1000);
    driver.findElement(By.id("mat-mdc-checkbox-1-input")).click();
    
    Thread.sleep(1000);
    driver.findElement(By.id("Fee per uur")).click();
    driver.findElement(By.id("Fee per uur")).sendKeys("100");
    
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    Thread.sleep(3000);
  }

  @AfterTest
	public void after() {
		driver.quit();
	}
}
