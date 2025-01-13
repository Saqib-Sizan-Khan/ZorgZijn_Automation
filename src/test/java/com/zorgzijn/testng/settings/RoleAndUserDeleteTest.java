package com.zorgzijn.testng.settings;

import com.zorgzijn.testng.setup.PerformAction;
import com.zorgzijn.testng.setup.ZorgzijnBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RoleAndUserDeleteTest extends ZorgzijnBaseTest {

    @Test(groups = "role-user-deletion")
    public void testNavigateToUserTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[6]/a"));
        PerformAction.shortWait();
    }

    @Test(groups = "role-user-deletion", dependsOnMethods = "testNavigateToUserTab")
    public void testSearchUser() throws InterruptedException {
        PerformAction.typeField(By.xpath("//input[@type='search']"), User.getUserFirstName());
    }

    @Test(groups = "role-user-deletion", dependsOnMethods = "testSearchUser")
    public void testDeleteUser() throws InterruptedException {
        User.openUserMenu(2);

        // Confirm Delete
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }

    @Test(groups = "role-user-deletion", dependsOnMethods = "testDeleteUser")
    public void testNavigateToRoleTab() throws InterruptedException {
        PerformAction.clickElement(By.xpath("//li[7]/a"));
    }

    @Test(groups = "role-user-deletion", dependsOnMethods = "testNavigateToRoleTab")
    public void testSearchRole() throws InterruptedException {
        PerformAction.typeField(By.xpath("//input[@type='search']"), Role.getRoleName());
    }

    @Test(groups = "role-user-deletion", dependsOnMethods = "testSearchRole")
    public void testDeleteRole() throws InterruptedException {
        Role.openRoleMenu(2);

        // Confirm Delete
        PerformAction.clickElement(By.xpath("//button[@type='submit']"));
        PerformAction.longWait();
    }
}
