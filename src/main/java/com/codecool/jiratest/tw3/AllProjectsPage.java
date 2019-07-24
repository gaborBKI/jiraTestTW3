package com.codecool.jiratest.tw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllProjectsPage {

    private Util util;

    @FindBy(id = "projects") private WebElement projectList;

    public AllProjectsPage(WebDriver driver) {
        util = new Util(driver);
    }

    public void clickOnValidProject(String projectName){
        util.waitFor(projectList);
        util.findElementByText(projectName).click();
    }

}
