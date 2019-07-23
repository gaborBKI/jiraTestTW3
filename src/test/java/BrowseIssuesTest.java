import com.codecool.jiratest.tw3.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrowseIssuesTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
    private static SearchIssuesPage searchIssuesPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"),"https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        searchIssuesPage = PageFactory.initElements(driver, SearchIssuesPage.class);
        navigate = new Navigate(driver);
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Order(1)
    @Test
    public void searchForIssuesTest() {
        if (dashBoardPage.verifyLogin()) {
            navigate.toPage("https://jira.codecool.codecanvas.hu/issues");
        }
        searchIssuesPage.searchIssue("none");
        Assert.assertEquals("none", searchIssuesPage.verifyIssue("none"));
    }
}