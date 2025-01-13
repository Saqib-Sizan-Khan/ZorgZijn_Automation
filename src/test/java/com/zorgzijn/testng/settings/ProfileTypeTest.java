package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProfileTypeTest extends ZorgzijnBaseTest {

    @Test(groups = "profiletype-test")
    public void testNavigateToProfileTypeTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[5]/a"));
    }

    @Test(groups = "profiletype-test", dependsOnMethods = "testNavigateToProfileTypeTab")
    public void testCreateProfileType() throws InterruptedException {

        // Open profile creation form
        PerformAction.clickElement(By.xpath("//app-profile-type/div/div/div/div/button"));

        // Fill out the form
        PerformAction.typeField(By.id("Typ Naam"), "Automation Profile");

        PerformAction.clickElement(By.xpath("//app-select-field[1]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[2]"));
        PerformAction.shortWait();

        PerformAction.clickElement(By.xpath("//app-select-field[2]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[2]"));
        PerformAction.shortWait();

        PerformAction.clickElement(By.xpath("//app-select-field[3]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[2]"));
        PerformAction.shortWait();

        // Submit the form
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.shortWait();
    }

    @Test(groups = "profiletype-test", dependsOnMethods = "testCreateProfileType")
    public void testModifyProfileType() throws InterruptedException {

        // Open profile menu
        PerformAction.clickElement(By.xpath("//tr[1]/td/button"));

        // Select the modify option
        PerformAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));

        // Modify the Profile type
        PerformAction.typeField(By.id("Typ Naam"), "Automation Modified Profile");

        PerformAction.clickElement(By.xpath("//app-select-field[1]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[1]"));
        PerformAction.shortWait();

        PerformAction.clickElement(By.xpath("//app-select-field[2]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[1]"));
        PerformAction.shortWait();

        PerformAction.clickElement(By.xpath("//app-select-field[3]/div/mat-select"));
        PerformAction.clickElement(By.xpath("//mat-option[1]"));
        PerformAction.shortWait();

        // Submit Changes
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.shortWait();
    }

    @Test(groups = "profiletype-test", dependsOnMethods = "testModifyProfileType")
    public void testDeleteProfileType() throws InterruptedException {

        // Open profile menu
        PerformAction.clickElement(By.xpath("//tr[1]/td/button"));

        // Select the delete option
        PerformAction.clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));

        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }
}
