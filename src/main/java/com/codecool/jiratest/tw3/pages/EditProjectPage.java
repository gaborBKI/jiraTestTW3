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
    private WebElement editButton;
    @FindBy(id="edit-issue-dialog")
    private WebElement editDialogBox;

    @FindBy(xpath = "//h1[@title='Click to edit']")
    private WebElement summaryHeader;
    @FindBy(id = "summary")
    private WebElement editableSummaryField;

    @FindBy(xpath = "//a[@id='opsbar-operations_more']/child::span[@class='dropdown-text']")
    private WebElement moreButton;
    @FindBy(xpath = "//*[@id='delete-issue']//descendant::span[@class='trigger-label']")
    private WebElement deleteButton;
    @FindBy(id = "delete-issue-submit")
    private WebElement confirmDeleteButton;

    public EditProjectPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void clickEdit() {
        util.waitFor(editButton, 6);
        editButton.click();
    }

    public boolean verifyEditButton(){
        util.waitFor(editDialogBox, 6);
        return editDialogBox.isDisplayed();
    }

    public void editSummaryField(String newSummary) {
        util.waitForElementClickable(summaryHeader);
        summaryHeader.click();
        util.waitFor(editableSummaryField, 6);
        editableSummaryField.sendKeys(newSummary);
        editableSummaryField.sendKeys(Keys.RETURN);
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
}
