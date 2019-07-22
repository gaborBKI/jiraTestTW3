package com.codecool.jiratest.tw3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserFactory {
    private static WebDriver webDriver;

    public static WebDriver loadPage(String browserName, String url){       //todo: browserName could be an environment variable

        if(browserName.equals("firefox")){
            webDriver= new FirefoxDriver();
        }
        else if(browserName.equals("chrome")){
            webDriver = new ChromeDriver();
        }
        webDriver.get(url);
        ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0, 1000)");
        return webDriver;
    }
}
