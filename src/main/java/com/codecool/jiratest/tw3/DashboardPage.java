package com.codecool.jiratest.tw3;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    private Util util;
    @FindBy(id = "header-details-user-fullname") private WebElement userButton;
    @FindBy(id = "log_out") private WebElement logOutButton;
    @FindBy(id = "login-container") private WebElement logInContainer;

    public DashboardPage(WebDriver driver) {
        util = new Util(driver);
    }

    public boolean verifyLogin(){
        try {
            util.waitFor(userButton);
        } catch (TimeoutException e){
            return false;
        }
        return userButton.isDisplayed();
    }


    public void waitForDashboard(){
        util.waitFor(userButton);
    }


    public void logOut(){
        util.waitFor(userButton);
        userButton.click();
        util.waitFor(logOutButton);
        logOutButton.click();
    }

    public WebElement getLogInContainer(){
        try {
            util.waitFor(logInContainer);
        } catch (TimeoutException e){
            return null;
        }
        return logInContainer;
    }

}
