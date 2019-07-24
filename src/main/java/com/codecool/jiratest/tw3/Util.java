package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Util {

    private final WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;
    }

    public void generalWait(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void waitFor(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClick(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
