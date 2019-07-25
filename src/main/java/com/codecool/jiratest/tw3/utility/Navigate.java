package com.codecool.jiratest.tw3.utility;

import org.openqa.selenium.WebDriver;

public class Navigate {

    private final WebDriver driver;

    public Navigate(WebDriver driver) {
        this.driver = driver;
    }

    public void toPage(String url){
        driver.get(url);
    }

    public void pressBackInBrowser(){
        driver.navigate().back();
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }
}
