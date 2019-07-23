package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditIssueSpecificPage {

    private Util util;
    @FindBy(id= "edit-issue") private WebElement editIssueButton;
    @FindBy(id= "edit-issue-dialog") private WebElement editIssueDialog;
    @FindBy(id= "summary") private WebElement summary;
    @FindBy(id= "summary-val") private WebElement summaryValue;
    @FindBy(css= "#summary") private WebElement cssSummary;
    @FindBy(id= "edit-issue-submit") private WebElement editIssueSubmit;
    @FindBy(id= "issuetype-field") private WebElement issueTypeField;
    @FindBy(className= "error") private WebElement errorClass;


    public EditIssueSpecificPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void waitForEditButton() {
        util.waitFor(editIssueButton);
    }

    public void waitForElement(WebElement element) {
        util.waitFor(element);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clear(WebElement element) {
        element.clear();
    }

    public WebElement returnError() {
        return errorClass;
    }

    public WebElement returnIssueType() {
        return issueTypeField;
    }

    public void deleteRequiredFields() {
        click(editIssueButton);
        waitForElement(editIssueDialog);
        click(summary);
        clear(summary);
        click(editIssueSubmit);
        waitForElement(errorClass);
        click(issueTypeField);
        clear(issueTypeField);
    }

    public void deleteIssueSummary() {
        click(summaryValue);
        waitForElement(cssSummary);
        clear(cssSummary);
        waitForElement(errorClass);
    }
}
