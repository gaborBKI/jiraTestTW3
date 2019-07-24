package com.codecool.jiratest.tw3;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.Key;
import java.util.List;
import java.util.NoSuchElementException;

public class CreateIssuePage {
    private Util util;
    @FindBy(id = "project-field")
    private WebElement projectBox;
    @FindBy(id = "issuetype-field")
    private WebElement issueBox;
    @FindBy(xpath = "//div[@id=\"issuetype-suggestions\"]//div[@class=\"aui-list-scroll\"]//ul[@class=\"aui-last\"]/li")
    private List<WebElement> issueTypeSuggestions;
    @FindBy(xpath = "//div[@id=\"issuetype-single-select\"]/child::span")
    private WebElement dropDownTrigger;
    @FindBy(xpath = "//a[text()=\"Cancel\" and @class=\"cancel\"]")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[@id='project-suggestions']/div[@tabindex='-1']/li[@class='no-suggestions']")
    private WebElement projectErroMessage;

    public CreateIssuePage(WebDriver driver) {
        this.util = new Util(driver);
    }

    public void sendMessageToProjectBox(String message){
        util.waitFor(projectBox);
        projectBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        projectBox.sendKeys(Keys.BACK_SPACE);
        projectBox.sendKeys(message );
        projectBox.sendKeys(Keys.ENTER);
    }

    public void sendMessageToIssueBox(String message){
        util.waitForElementClickable(issueBox);
        issueBox.click();
        issueBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        issueBox.sendKeys(Keys.BACK_SPACE);
    }

    public boolean FindValueInIssueValues(String value){
        util.waitForElementClickable(dropDownTrigger);
        dropDownTrigger.click();
        for (WebElement issueTypeSuggestion : issueTypeSuggestions) {
            if(value.equals(issueTypeSuggestion.getText())) return true;
            System.out.println("IssueTypeSuggestion: "+ issueTypeSuggestion.getText());
        }
        return false;
    }

    public void clickToCancel(){
        cancelButton.click();
    }

    public boolean projectError(){
        try {
            util.waitFor(projectErroMessage);
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }
}
