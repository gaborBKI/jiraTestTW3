package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    private Util util;
    @FindBy(id= "header-details-user-fullname") private WebElement userButton;

    public DashboardPage(WebDriver driver) {
        util = new Util(driver);
    }

    public boolean verifyUserButton(){
        util.waitFor(userButton);
        return userButton.isDisplayed();
    }

}
