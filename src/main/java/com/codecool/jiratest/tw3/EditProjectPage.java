package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProjectPage {

    private Util util;

    @FindBy(id="edit-issue")
    private WebElement editButton;

    @FindBy(id="edit-issue-dialog")
    private WebElement editDialogBox;


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

}
