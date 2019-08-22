import com.codecool.jiratest.tw3.env.Creds;
import com.codecool.jiratest.tw3.pages.CreateIssuePage;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.CapabilityLoader;
import com.codecool.jiratest.tw3.utility.Navigate;
import com.codecool.jiratest.tw3.utility.Util;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class createIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static CreateIssuePage createIssuePage;
    private static DashboardPage dashBoardPage;
    private static LoginPage loginPage;
    private static String baseUrl;
    private static String nodeURL;

    @BeforeAll
    public static void init() throws MalformedURLException {
        DesiredCapabilities capability = CapabilityLoader.setCapability();
        baseUrl = Util.getBaseURL();
        nodeURL = Util.getNodeURL();
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        createIssuePage = PageFactory.initElements(driver, CreateIssuePage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        navigate = new Navigate(driver);
        navigate.toPage(baseUrl);
        loginPage.userLogin(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
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
