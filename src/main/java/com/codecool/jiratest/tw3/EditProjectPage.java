package com.codecool.jiratest.tw3;

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

    public EditProjectPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void clickEdit() {
        util.waitFor(editButton);
        editButton.click();
    }

    public boolean verifyEditButton(){
        util.waitFor(editDialogBox);
        return editDialogBox.isDisplayed();
    }

    public void editSummaryField(String newSummary) {
        util.waitForElementClickable(summaryHeader);
        summaryHeader.click();
        util.waitFor(editableSummaryField);
        editableSummaryField.sendKeys(newSummary);
        editableSummaryField.sendKeys(Keys.RETURN);
    }

    public String getSummaryText() {
        util.waitForElementClickable(summaryHeader);
        return summaryHeader.getText();
    }
}
