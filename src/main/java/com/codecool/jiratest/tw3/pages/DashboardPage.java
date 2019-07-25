package com.codecool.jiratest.tw3.pages;

import com.codecool.jiratest.tw3.utility.Util;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    private Util util;
    @FindBy( id = "create_link")
    private WebElement createButton;
    @FindBy(id = "header-details-user-fullname") private WebElement userButton;
    @FindBy(id = "log_out") private WebElement logOutButton;
    @FindBy(id = "login-container") private WebElement logInContainer;
    @FindBy(xpath = "//div[@id=\"aui-flag-container\"]//descendant::a[@class=\"issue-created-key issue-link\"]") private WebElement newIssueMessageLink;

    public DashboardPage(WebDriver driver) {
        util = new Util(driver);
    }

    public boolean verifyLogin(){
        try {
            util.waitFor(userButton, 6);
        } catch (TimeoutException e){
            return false;
        }
        return userButton.isDisplayed();
    }

    public void clickToCreateIssue(){
        util.waitFor(createButton, 6);
        createButton.click();
    }

    public void waitForDashboard() {
        util.waitFor(userButton, 6);
    }

    public void logOut(){
        util.waitFor(userButton, 6);
        userButton.click();
        util.waitFor(logOutButton, 6);
        logOutButton.click();
    }

    public WebElement getLogInContainer(){
        try {
            util.waitFor(logInContainer, 6);
        } catch (TimeoutException e){
            return null;
        }
        return logInContainer;
    }

    public void catchCreatePopUpWindow() {
        util.waitFor(newIssueMessageLink, 10);
        newIssueMessageLink.click();
    }

}
