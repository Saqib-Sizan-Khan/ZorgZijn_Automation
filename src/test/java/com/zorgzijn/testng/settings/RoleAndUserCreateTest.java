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
    public void testCreateRole() throws InterruptedException {

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

    @Test(groups = "role-user-creation", dependsOnMethods = "testCreateRole")
    public void testNavigateToUserTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[6]/a"));
        PerformAction.shortWait();
    }

    @Test(groups = "role-user-creation", dependsOnMethods = "testNavigateToUserTab")
    public void testCreateUser() throws InterruptedException {

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
}
