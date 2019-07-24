package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReLoginPage {

    private Util util;
    @FindBy(xpath = "//a[@href='/login.jsp']") private WebElement reLoginLink;
    @FindBy(id = "login-form-username") private WebElement userName;
    @FindBy(id = "login-form-password") private WebElement password;
    @FindBy(id = "login-form-submit") private WebElement submitButton;

    public ReLoginPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void reLogIn(String username, String pass){
        reLoginLink.click();
        util.waitFor(userName, 6);
        userName.sendKeys(username);
        password.sendKeys(pass);
        submitButton.click();
    }

}
