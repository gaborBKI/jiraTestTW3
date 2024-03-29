package com.codecool.jiratest.tw3.pages;

import com.codecool.jiratest.tw3.utility.Util;
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
        util.waitFor(projectList, 6);
        util.findElementByText(projectName).click();
    }

}
