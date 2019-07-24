import com.codecool.jiratest.tw3.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class createIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static CreateIssuePage createIssuePage;
    private static DashboardPage dashBoardPage;
    private static LoginPage loginPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"),"https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        createIssuePage = PageFactory.initElements(driver, CreateIssuePage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        navigate = new Navigate(driver);
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/createIssueRes.csv", numLinesToSkip = 1)
    public void testIssueTypesWithProjects(String projectName, String issueType){
        dashBoardPage.clickToCreateIssue();
        createIssuePage.sendMessageToProjectBox(projectName);
        Assert.assertFalse(createIssuePage.projectError());
        createIssuePage.sendMessageToIssueBox(issueType);
        boolean valueFound = createIssuePage.FindValueInIssueValues(issueType);
        Assert.assertTrue(valueFound);
    }

    @AfterEach
    public void exit(){
        createIssuePage.clickToCancel();
    }

    @AfterAll
    public static void quit(){
        driver.quit();
    }
}
