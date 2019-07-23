package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

    private final WebDriver driver;

    private WebDriverWait wait;

    public Util(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitFor(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitFor(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
