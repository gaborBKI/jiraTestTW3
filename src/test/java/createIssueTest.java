import com.codecool.jiratest.tw3.pages.CreateIssuePage;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
        driver = BrowserFactory.loadPage(System.getenv("driverType"));
        createIssuePage = PageFactory.initElements(driver, CreateIssuePage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        navigate = new Navigate(driver);
        navigate.toPage(System.getenv("LOGIN_PAGE"));
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/createIssueRes.csv", numLinesToSkip = 1)
    public void testIssueTypesWithProjects(String projectName, String issueType){
        dashBoardPage.clickToCreateIssue();
        createIssuePage.sendMessageToProjectField(projectName);
        Assert.assertFalse(createIssuePage.projectErrorOccured());
        createIssuePage.sendMessageToIssueField(issueType);
        boolean valueFound = createIssuePage.findIssueBetweenIssues(issueType);
        Assert.assertTrue("message", valueFound);
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
