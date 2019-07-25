package com.codecool.jiratest.tw3;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseProjectsPage {

    private Util util;
    @FindBy(id = "browse_link") private WebElement projectsButton;
    @FindBy(id = "project_view_all_link_lnk") private WebElement viewAllProjectsButton;
    @FindBy(id = "projects") private WebElement projectList;
    @FindBy(xpath = "//*[@id='header']/nav") private WebElement navBar;

    public BrowseProjectsPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void getToProjectsFromDropdown(){
        util.waitFor(navBar, 8);
        projectsButton.click();
        util.waitFor(viewAllProjectsButton, 6);
        viewAllProjectsButton.click();
    }

    public boolean verifyProjectListAppears(){
        try {
            util.waitFor(projectList, 6);
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }

}
