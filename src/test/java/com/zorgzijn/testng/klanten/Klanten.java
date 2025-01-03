package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import lombok.Getter;
import org.openqa.selenium.By;

public class Klanten {

    PerformAction performAction = new PerformAction();

    @Getter
    private static String clientName;

    public static void setClientName(String clientName) {
        Klanten.clientName = clientName;
    }

    public void searchAndShowClient(String client) throws InterruptedException {
        PerformAction.shortWait();
        performAction.typeField(By.id("simple-search"), client);
        PerformAction.longWait();
        performAction.clickElement(By.xpath("//client-list/div/div[1]"));
        PerformAction.shortWait();
    }

    public void openClientMenu(int menuItem) throws InterruptedException {
        performAction.clickElement(By.xpath("//client-details/div/client-header/div/div[2]/button"));
        performAction.clickElement(By.xpath("//button[@role='menuitem']["+ menuItem +"]"));
    }
}
