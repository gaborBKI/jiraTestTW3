import com.codecool.jiratest.tw3.env.Creds;
import com.codecool.jiratest.tw3.pages.AllProjectsPage;
import com.codecool.jiratest.tw3.pages.BrowseProjectsPage;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.CapabilityLoader;
import com.codecool.jiratest.tw3.utility.Navigate;
import com.codecool.jiratest.tw3.utility.Util;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrowseProjectsTest {

    /* Environment variables used in this test:

    JIRAUSER = username
    PASSWROD = password
    driverType = can be firefox or chrome
    ALL_PROJECTS_URL = https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all

     */

    private static String projectToTest = "Main Testing Project";
    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static BrowseProjectsPage browseProjectsPage;
    private static AllProjectsPage allProjectsPage;
    private static DashboardPage dashboardPage;
    private static String baseUrl;
    private static String nodeURL;

    @BeforeAll
    public static void init() throws MalformedURLException {
        DesiredCapabilities capability = CapabilityLoader.setCapability();
        baseUrl = Util.getBaseURL();
        nodeURL = "http://10.44.1.192:5566/wd/hub";
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        browseProjectsPage = PageFactory.initElements(driver, BrowseProjectsPage.class);
        allProjectsPage = PageFactory.initElements(driver, AllProjectsPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        navigate = new Navigate(driver);
    }

    @BeforeEach
    public void logIn(){
        navigate.toPage(baseUrl);
        loginPage.userLogin(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
    }

    @AfterEach
    public void logOut(){
        dashboardPage.logOut();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void projectListAppearsTest() {
        browseProjectsPage.getToProjectsFromDropdown(); // get returns something, refactor it!
        Assert.assertTrue(browseProjectsPage.verifyProjectListAppears());
    }

    @Test
    public void projectPageValidTest() {
        navigate.toPage(System.getenv("ALL_PROJECTS_URL"));
        allProjectsPage.clickOnValidProject(projectToTest);
        String expectedURL = "https://jira.codecool.codecanvas.hu/projects/MTP/issues";
        Assert.assertEquals(expectedURL, navigate.getCurrentURL());
    }

}
