package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import com.zorgzijn.testng.utils.RandomInput;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RoleAndUserModifyTest extends ZorgzijnBaseTest {

    @Test(groups = "role-user-modification")
    public void testNavigateToRoleTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[7]/a"));
    }

    @Test(groups = "role-user-modification", dependsOnMethods = "testNavigateToRoleTab")
    public void testSearchRole() throws InterruptedException {
        PerformAction.typeField(By.xpath("//input[@type='search']"), Role.getRoleName());
    }

    @Test(groups = "role-user-modification", dependsOnMethods = "testSearchRole")
    public void testModifyRole() throws InterruptedException {
        Role.openRoleMenu(1);
        Role.setRoleName("Modified Automation Role");

        // Change Role Name
        PerformAction.typeField(By.id("Rol naam"), Role.getRoleName());

        // Update permissions
        Role.selectRole(2);
        Role.selectRole(4);
        Role.selectRole(5);
        Role.selectRole(7);

        // Submit changes
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }

    @Test(groups = "role-user-modification", dependsOnMethods = "testModifyRole")
    public void testNavigateToUserTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[6]/a"));
        PerformAction.shortWait();
    }

    @Test(groups = "role-user-modification", dependsOnMethods = "testNavigateToUserTab")
    public void testSearchUser() throws InterruptedException {
        PerformAction.typeField(By.xpath("//input[@type='search']"), User.getUserFirstName());
    }

    @Test(groups = "role-user-modification", dependsOnMethods = "testSearchUser")
    public void testModifyUser() throws InterruptedException {
        User.openUserMenu(1);

        //Change username
        User.setFirstName("Modified");
        User.setMiddleName("Selenium");
        User.setLastName("User");
        PerformAction.typeField(By.id("Voornaam"), User.getUserFirstName());
        PerformAction.typeField(By.id("Tussenvoegsel"), User.getUserMiddleName());
        PerformAction.typeField(By.id("achternaam"), User.getUserLastName());

        //Change DOB
        PerformAction.typeField(By.id("Geboortedatum"), RandomInput.birthday());

        // Submit changes
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }
}
