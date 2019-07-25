import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.pages.ReLoginPage;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
    private static ReLoginPage reLoginPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"));
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        reLoginPage = PageFactory.initElements(driver, ReLoginPage.class);
        navigate = new Navigate(driver);
    }

    @BeforeEach
    public void navigate(){
        navigate.toPage(System.getenv("LOGIN_PAGE"));
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
        dashBoardPage.logOut();
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Order(1)
    @Test
    public void successfulLogoutTest(){
        Assert.assertEquals("Log In", loginPage.verifyLoggedOutState());
    }

    @Order(2)
    @Test
    public void backOnLogOutPageTest(){
        navigate.pressBackInBrowser();
        Assert.assertNotNull(dashBoardPage.getLogInContainer());
    }

    @Order(3)
    @Test
    public void reLogInTest(){
        reLoginPage.reLogIn(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
        Assert.assertTrue(dashBoardPage.verifyLogin());
    }

}
