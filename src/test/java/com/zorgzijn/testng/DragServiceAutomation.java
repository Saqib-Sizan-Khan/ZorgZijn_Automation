package com.zorgzijn.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DragServiceAutomation extends AutomationSetupClass {

    private static final int SHORT_WAIT = 1500;
    private static final int LONG_WAIT = 2000;

    int tableRowPosition = 2;
    int tableColumnPosition = 2;
    String employeeName = "Ella Victoria Rogers";


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
        Thread.sleep(5000);
    }

    @Test(priority = 3, dependsOnMethods = "navigateToPlanningTab")
    public void dragDropService() throws InterruptedException {

        //Open the default service section
        clickElement(By.xpath("//tr[@id='stickyHeader'][1]//div/div"));
        Thread.sleep(LONG_WAIT);

        //Grab the service
        WebElement shift = driver.findElement(By.xpath("//shift-type[1]"));

        //Select the date
        WebElement dateBox = driver.findElement(By.xpath("//tbody/div[1]/tr["+ tableRowPosition +"]/td["+ tableColumnPosition +"]/div/div"));

        Thread.sleep(SHORT_WAIT);

        System.out.println("Shift element: " + shift.isDisplayed());
        System.out.println("DateBox element: " + dateBox.isDisplayed());

        Actions actions = new Actions(driver);

        actions.clickAndHold(shift).pause(2000).moveToElement(dateBox).release().build().perform();

        Thread.sleep(LONG_WAIT);
    }

//    @Test(priority = 4, dependsOnMethods = "createService")
//    public void navigateToTimeRegistrationTab() throws InterruptedException {
//        clickElement(By.xpath("//div/a[3]"));
//    }
//
//    @Test(priority = 5, dependsOnMethods = "navigateToTimeRegistrationTab")
//    public void registerService() throws InterruptedException {
//        // Change date
//        clickElement(By.xpath("//button[1]"));
//        Thread.sleep(SHORT_WAIT);
//
//        // Find and select employee checkbox
//        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
//
//        for (WebElement row : rows) {
//            WebElement nameCell = row.findElement(By.xpath(".//td[1]"));
//
//            if (nameCell.getText().equalsIgnoreCase(employeeName)) {
//                // Register the service
//                WebElement checkbox = row.findElement(By.xpath(".//button[2]"));
//                checkbox.click();
//                break;
//            }
//        }
//        Thread.sleep(LONG_WAIT);
//    }
//
//    @Test(priority = 6, dependsOnMethods = "registerService")
//    public void navigateToInvoiceTab() throws InterruptedException {
//        clickElement(By.xpath("//div/a[6]"));
//    }
//
//    @Test(priority = 7, dependsOnMethods = "navigateToInvoiceTab")
//    public void invoiceService() throws InterruptedException {
//        // Select location
//        sendKeysToElement(By.xpath("//div[2]/app-autocomplete-field/div/input"), "Medical");
//        clickElement(By.tagName("mat-option"));
//
//        // Find and select employee checkbox
//        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
//
//        for (WebElement row : rows) {
//            WebElement nameCell = row.findElement(By.xpath(".//td[1]"));
//
//            if (nameCell.getText().equalsIgnoreCase(employeeName)) {
//                WebElement checkbox = row.findElement(By.xpath(".//td/mat-checkbox"));
//                checkbox.click();
//                break;
//            }
//        }
//        Thread.sleep(LONG_WAIT);
//
//        // Confirm invoice
//        clickElement(By.xpath("//submit-button[2]/button"));
//
//        // Check invoice status
//        clickElement(By.xpath("//app-select-field/div/mat-select"));
//        Thread.sleep(LONG_WAIT);
//        clickElement(By.xpath("//mat-option[2]"));
//        Thread.sleep(LONG_WAIT);
//    }
}
