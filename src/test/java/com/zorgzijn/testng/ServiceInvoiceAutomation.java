package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ServiceInvoiceAutomation extends AutomationSetupClass {

    private static final int SHORT_WAIT = 1500;
    private static final int LONG_WAIT = 2000;

    int tableRowPosition = 9;
    String employeeName = "Jewel Chowdhury MR Chowdhury";


    private void clickElement(By locator) throws InterruptedException {
        driver.findElement(locator).click();
        Thread.sleep(SHORT_WAIT);
    }

    private void sendKeysToElement(By locator, String keys) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(keys);
        Thread.sleep(SHORT_WAIT);
    }

    private void waitForPageLoad() throws InterruptedException {
        Thread.sleep(LONG_WAIT);
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get(baseUrl + "/auth/login");
        waitForPageLoad();

        sendKeysToElement(By.id("E-mailadres"), "ssk123098@gmail.com");
        sendKeysToElement(By.id("Wachtwoord"), "Sizan@1999");
        clickElement(By.id("remember"));

        // Submit the login form
        clickElement(By.xpath("//button"));
        waitForPageLoad();
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void navigateToPlanningTab() throws InterruptedException {
        clickElement(By.xpath("//div/a[2]"));
        Thread.sleep(5000); // Consider replacing with explicit wait
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPlanningTab")
    public void createService() throws InterruptedException {
        // Open service creation form
        clickElement(By.xpath("//tbody/div[1]/tr["+ tableRowPosition +"]/td[4]"));
        clickElement(By.tagName("mat-select"));
        clickElement(By.xpath("//mat-option[2]"));
        sendKeysToElement(By.tagName("textarea"), "This is an automated text");

        // Submit form to create the service
        clickElement(By.xpath("//form/div/button[1]"));
        Thread.sleep(SHORT_WAIT);
    }

    @Test(priority = 4, dependsOnMethods = "createService")
    public void navigateToTimeRegistrationTab() throws InterruptedException {
        clickElement(By.xpath("//div/a[3]"));
    }

    @Test(priority = 5, dependsOnMethods = "navigateToTimeRegistrationTab")
    public void registerService() throws InterruptedException {
        // Change date
        clickElement(By.xpath("//button[1]"));

        // Find and select employee checkbox
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath(".//td[1]"));

            if (nameCell.getText().equalsIgnoreCase(employeeName)) {
                // Register the service
                WebElement checkbox = row.findElement(By.xpath(".//button[2]"));
                checkbox.click();
                break;
            }
        }
        Thread.sleep(LONG_WAIT);
    }

    @Test(priority = 6, dependsOnMethods = "registerService")
    public void navigateToInvoiceTab() throws InterruptedException {
        clickElement(By.xpath("//div/a[6]"));
    }

    @Test(priority = 7, dependsOnMethods = "navigateToInvoiceTab")
    public void invoiceService() throws InterruptedException {
        // Select location
        sendKeysToElement(By.xpath("//div[2]/app-autocomplete-field/div/input"), "Medical");
        clickElement(By.tagName("mat-option"));

        // Find and select employee checkbox
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath(".//td[1]"));

            if (nameCell.getText().equalsIgnoreCase(employeeName)) {
                WebElement checkbox = row.findElement(By.xpath(".//td/mat-checkbox"));
                checkbox.click();
                break;
            }
        }
        Thread.sleep(LONG_WAIT);

        // Confirm invoice
        clickElement(By.xpath("//submit-button[2]/button"));

        // Check invoice status
        clickElement(By.xpath("//app-select-field/div/mat-select"));
        Thread.sleep(LONG_WAIT);
        clickElement(By.xpath("//mat-option[2]"));
        Thread.sleep(LONG_WAIT);
    }
}
