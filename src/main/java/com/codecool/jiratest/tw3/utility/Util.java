package com.codecool.jiratest.tw3.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Util {

    private final WebDriver driver;

    private WebDriverWait wait;
    private static String baseURL = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
    private static String nodeURL = "http://" + System.getProperty("node") + "/wd/hub";

    public Util(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getNodeURL() {
        return nodeURL;
    }

    public void generalWait(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void waitFor(WebElement element, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitFor(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public WebElement findElementByText(String text){
        return driver.findElement(By.linkText(text));
    }

    public void waitForElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
            // Search for " I Am A Little Tester"
}
