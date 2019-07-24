package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchIssuesPage {

    private Util util;
    @FindBy(id= "searcher-query") private WebElement searchBox;
    @FindBy(xpath= "//*[@original-title='Search for issues']") private WebElement searchButton;
    @FindBy(id= "summary-val") private WebElement issueName;


    public SearchIssuesPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void searchIssue(String issue) {
        util.waitFor(searchBox, 6);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(issue);
        searchButton.click();
    }

    public String verifyIssueText(String searchText) {
        util.waitFor(issueName, searchText, 6);
        return issueName.getText();
    }

    public WebElement verifyIssue() {
        util.waitFor(issueName, 6);
        return issueName;
    }
}
