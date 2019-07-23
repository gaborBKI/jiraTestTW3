import com.codecool.jiratest.tw3.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrowseProjectsTest {

    private static String projectToTest = "Main Testing Project";
    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static BrowseProjectsPage browseProjectsPage;
    private static AllProjectsPage allProjectsPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"),"https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        browseProjectsPage = PageFactory.initElements(driver, BrowseProjectsPage.class);
        allProjectsPage = PageFactory.initElements(driver, AllProjectsPage.class);
        navigate = new Navigate(driver);
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Order(1)
    @Test
    public void projectListAppearsTest() {
        browseProjectsPage.getToProjectsFromDropdown();
        Assert.assertTrue(browseProjectsPage.verifyProjectListAppears());
    }

    @Order(2)
    @Test
    public void projectPageValidTest(){
        navigate.toPage("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        allProjectsPage.validateProjectIsOnPage(projectToTest);
        String expectedURL ="https://jira.codecool.codecanvas.hu/projects/MTP/issues";
        Assert.assertEquals(expectedURL, navigate.getCurrentURL());

    }

}
