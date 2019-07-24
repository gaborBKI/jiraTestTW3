import com.codecool.jiratest.tw3.BrowserFactory;
import com.codecool.jiratest.tw3.DashboardPage;
import com.codecool.jiratest.tw3.LoginPage;
import com.codecool.jiratest.tw3.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"),"https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        navigate = new Navigate(driver);
        navigate.toLoginPage();
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Test
    public void happyPathTest(){
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
        Assert.assertTrue(dashBoardPage.verifyLogin());
    }




}
