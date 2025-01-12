package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RoleAndUserCreateTest extends ZorgzijnBaseTest {

    @Test(groups = "role-user-creation")
    public void testNavigateToRoleTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[7]/a"));
    }

    @Test(groups = "role-user-creation", dependsOnMethods = "testNavigateToRoleTab")
    public void createRole() throws InterruptedException {

        PerformAction.clickElement(By.xpath("//app-role//div[1]/div/button"));

        //Set Role Name
        PerformAction.typeField(By.id("Rol naam"), Role.getRoleName());

        // Select specific permissions
        Role.selectRole(1);
        Role.selectRole(3);
        Role.selectRole(5);
        Role.selectRole(7);

        // Submit the form
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }

    @Test(groups = "role-user-creation", dependsOnMethods = "createRole")
    public void testNavigateToUserTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[6]/a"));
        PerformAction.shortWait();
    }

    @Test(groups = "role-user-creation", dependsOnMethods = "testNavigateToUserTab")
    public void createUser() throws InterruptedException {

        PerformAction.clickElement(By.xpath("//users/div/div/div/div//button"));

        //Set username
        PerformAction.typeField(By.id("Voornaam"), User.getUserFirstName());
        PerformAction.typeField(By.id("Tussenvoegsel"), User.getUserMiddleName());
        PerformAction.typeField(By.id("Achternaam"), User.getUserLastName());

        //Set email
        PerformAction.typeField(By.id("E-mailadres"), RandomInput.email());

        //Set Gender
        PerformAction.clickElement(By.tagName("mat-select"));
        PerformAction.clickElement(By.cssSelector("mat-option:nth-of-type(1)"));

        //Set DOB
        PerformAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());

        //Set role
        PerformAction.typeField(By.xpath("//app-autocomplete-field//input"), Role.getRoleName());
        PerformAction.clickElement(By.tagName("mat-option"));

        // Submit the form
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }

//    @Test(priority = 4, dependsOnMethods = "createRole")
//    public void searchRole() throws InterruptedException {
//        sendKeysToElement(By.xpath("//input[@type='search']"), "Automation");
//        Thread.sleep(3000); // Simulate search delay
//
//        // Clear the search field
//        driver.findElement(By.xpath("//input[@type='search']")).clear();
//        Thread.sleep(2000);
//    }

//    @Test(priority = 5, dependsOnMethods = "searchRole")
//    public void modifyRole() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));
//
//        sendKeysToElement(By.xpath("//div/div/input"), "Role Automation");
//
//        // Update permissions
//        clickElement(By.xpath("//li[2]/div/input"));
//        clickElement(By.xpath("//li[4]/div/input"));
//        clickElement(By.xpath("//li[5]/div/input"));
//        clickElement(By.xpath("//li[7]/div/input"));
//
//        // Submit changes
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
//
//    @Test(priority = 6, dependsOnMethods = "modifyRole")
//    public void giveRoleAllAccess() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(1)"));
//
//        sendKeysToElement(By.xpath("//div/div/input"), "Role Automation with all access");
//
//        // Grant all permissions
//        clickElement(By.id("ALL"));
//
//        // Submit changes
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
//
//    private void openDeleteDialog() throws InterruptedException {
//        clickElement(By.xpath("//div[2]/div/button"));
//        clickElement(By.cssSelector("button.mat-mdc-menu-item:nth-of-type(2)"));
//    }
//
//    @Test(priority = 7, dependsOnMethods = "giveRoleAllAccess")
//    public void deleteRole() throws InterruptedException {
//        // Cancel the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//div[2]/div/div/div[1]/button"));
//
//        //Close the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//div[2]/div/div/div[3]/button[2]"));
//
//        // Confirm the delete action
//        openDeleteDialog();
//        clickElement(By.xpath("//button[@type='submit']"));
//    }
}
