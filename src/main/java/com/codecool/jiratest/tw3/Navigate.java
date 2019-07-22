package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;

public class Navigate {

    private final WebDriver driver;

    public Navigate(WebDriver driver) {
        this.driver = driver;
    }

    public void toLoginPage(){
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }
}
