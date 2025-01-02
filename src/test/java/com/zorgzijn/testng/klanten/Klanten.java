package com.zorgzijn.testng.klanten;

import com.zorgzijn.testng.setup.PerformAction;
import org.openqa.selenium.By;

public class Klanten {

    private static String clientName;

    public static String getClientName() {
        return clientName;
    }

    public static void setClientName(String clientName) {
        Klanten.clientName = clientName;
    }

    public static void searchAndShowClient(String client) throws InterruptedException {
        PerformAction.shortWait();
        PerformAction.typeField(By.id("simple-search"), client);
        PerformAction.longWait();
        PerformAction.clickElement(By.xpath("//client-list/div/div[1]"));
        PerformAction.shortWait();
    }

    public static void openClientMenu(int menuItem) throws InterruptedException {
        PerformAction.clickElement(By.xpath("//client-details/div/client-header/div/div[2]/button"));
        PerformAction.clickElement(By.xpath("//button[@role='menuitem']["+ menuItem +"]"));
    }
}
