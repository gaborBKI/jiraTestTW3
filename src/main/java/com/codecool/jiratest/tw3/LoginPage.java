package com.codecool.jiratest.tw3;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    private Util util;
    @FindBy(id= "login-form-username") private WebElement userName;
    @FindBy(id= "login-form-password") private WebElement password;
    @FindBy(id= "login") private WebElement loginBtn;
    @FindBy(id = "user-options") private WebElement userOptions;


    public LoginPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void userLogin(String username, String pass) {
        util.waitFor(userName);
        userName.sendKeys(username);
        password.sendKeys(pass);
        loginBtn.click();
    }

    public String verifyLoggedOutState(){
        try {
            util.waitFor(userOptions);
        } catch (TimeoutException e){
            return "Error";
        }
        return userOptions.getText();
    }
}
