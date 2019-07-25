package com.codecool.jiratest.tw3.pages;

import com.codecool.jiratest.tw3.utility.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProjectPage {

    private Util util;
    WebDriverWait wait;

    @FindBy(id="edit-issue")
    private WebElement editIssueButton;
    @FindBy(id="edit-issue-dialog")
    private WebElement editIssueDialog;

    @FindBy(xpath = "//h1[@title='Click to edit']")
    private WebElement summaryHeader;
    @FindBy(id = "summary")
    private WebElement summary;

    @FindBy(xpath = "//a[@id='opsbar-operations_more']/child::span[@class='dropdown-text']")
    private WebElement moreButton;
    @FindBy(xpath = "//*[@id='delete-issue']//descendant::span[@class='trigger-label']")
    private WebElement deleteButton;
    @FindBy(id = "delete-issue-submit")
    private WebElement confirmDeleteButton;

    @FindBy(id= "summary-val") private WebElement summaryValue;
    @FindBy(id= "summary-form") private WebElement summaryTextField;
    @FindBy(id= "summary") private WebElement summaryText;
    @FindBy(id= "edit-issue-submit") private WebElement editIssueSubmit;
    @FindBy(id= "issuetype-field") private WebElement issueTypeField;
    @FindBy(id= "type-val") private WebElement issueTypeText;
    @FindBy(xpath = "//span[@id='type-val']/img[@alt='']") private WebElement issueTypeSubmit;
    @FindBy(xpath= "//*[@id=\"issuetype-field\"]") private WebElement issueTypeDropdown;
    @FindBy(className= "error") private WebElement errorClass;
    @FindBy(id= "aui-uid-1") private WebElement descriptionField;
    @FindBy(id= "description-wiki-edit") private WebElement descriptionBox;
    @FindBy(id= "description") private WebElement descriptionText;
    @FindBy(id= "description-val") private WebElement descriptionValue;


    public EditProjectPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void clickEdit() {
        util.waitFor(editIssueButton, 6);
        editIssueButton.click();
    }

    public boolean verifyEditButton(){
        util.waitFor(editIssueDialog, 6);
        return editIssueDialog.isDisplayed();
    }

    public void editSummaryField(String newSummary) {
        util.waitForElementClickable(summaryHeader);
        summaryHeader.click();
        util.waitFor(summary, 6);
        summary.sendKeys(newSummary);
        summary.sendKeys(Keys.RETURN);
    }

    public String getSummaryText() {
        util.waitForElementClickable(summaryHeader);
        return summaryHeader.getText();
    }

    public void deleteThisIssue() {
        util.waitFor(moreButton, 10);
        moreButton.click();
        util.waitFor(deleteButton, 10);
        deleteButton.click();
        util.waitFor(confirmDeleteButton, 10);
        confirmDeleteButton.click();
    }

    public void waitForEditButton() {
        util.waitFor(editIssueButton, 6);
    }

    public void waitForElement(WebElement element) {
        util.waitFor(element, 6);
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
        waitForElement(summaryTextField);
        clear(summaryText);
        waitForElement(errorClass);
    }

    public void navigateToDescriptionBox() {
        click(editIssueButton);
        waitForElement(descriptionField);
        click(descriptionField);
        click(descriptionBox);
    }

    public void editDescriptionBox(String newText) {
        clear(descriptionText);
        writeText(descriptionText, newText);
        click(editIssueSubmit);
    }

}
