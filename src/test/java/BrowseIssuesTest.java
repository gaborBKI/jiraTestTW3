import com.codecool.jiratest.tw3.env.Creds;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.pages.SearchIssuesPage;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.CapabilityLoader;
import com.codecool.jiratest.tw3.utility.Navigate;
import com.codecool.jiratest.tw3.utility.Util;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrowseIssuesTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
    private static SearchIssuesPage searchIssuesPage;
    private static String baseUrl;
    private static String nodeURL;

    @BeforeAll
    public static void init() throws MalformedURLException {
        DesiredCapabilities capability = CapabilityLoader.setCapability();
        baseUrl = Util.getBaseURL();
        nodeURL = Util.getNodeURL();
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        searchIssuesPage = PageFactory.initElements(driver, SearchIssuesPage.class);
        navigate = new Navigate(driver);
        navigate.toPage(baseUrl);
        loginPage.userLogin(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void searchForIssuesTest() {
        if (dashBoardPage.verifyLogin()) {
            navigate.toPage("https://jira.codecool.codecanvas.hu/issues");
        }
        searchIssuesPage.searchIssue("none");
        Assert.assertEquals("none", searchIssuesPage.verifyIssueText("none"));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = {"/coalaurl.csv", "/jetiurl.csv", "/toucanurl.csv"}, delimiter = '\n')
    public void projectsContainThreeIssues(String url){
        navigate.toPage(url);
        Assert.assertNotNull(searchIssuesPage.verifyIssue());
    }
}