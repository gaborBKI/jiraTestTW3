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
    @FindBy(id= "type-val") private WebElement issueTypeText;
    @FindBy(xpath= "//*[@id=\"issuetype-field\"]") private WebElement issueTypeDropdown;
    @FindBy(className= "error") private WebElement errorClass;
    @FindBy(xpath = "//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form") private WebElement descriptionForm;
    @FindBy(id= "aui-uid-1") private WebElement descriptionField;
    @FindBy(id= "description-wiki-edit") private WebElement descriptionBox;
    @FindBy(id= "description") private WebElement descriptionText;
    @FindBy(id= "description-val") private WebElement descriptionValue;


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

    public void writeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public WebElement returnError() {
        return errorClass;
    }

    public WebElement returnIssueType() {
        return issueTypeField;
    }

    public WebElement returnDescriptionValue() {
        return descriptionValue;
    }

    public WebElement returnIssueTypeText() { return issueTypeText; }

    public String returnText(WebElement element) {
        return element.getText();
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

    public void editDescription() {
        click(editIssueButton);
        waitForElement(descriptionForm);
        waitForElement(descriptionField);
        click(descriptionField);
        click(descriptionBox);
        clear(descriptionText);
        writeText(descriptionText, "Test");
        click(editIssueSubmit);
    }

    public void editIssueType() {
        waitForElement(editIssueButton);
        waitForElement(descriptionForm);
        waitForElement(issueTypeField);
        click(issueTypeDropdown);
        writeText(issueTypeDropdown, "Task");
        click(editIssueSubmit);
    }
}