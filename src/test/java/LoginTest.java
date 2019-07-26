import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.utility.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"));  //basepage for this
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        navigate = new Navigate(driver);
        navigate.toPage(System.getenv("LOGIN_PAGE"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Test
    @Order(1)
    public void emptyFieldsTest(){
        loginPage.userLogin("", "");
        Assert.assertFalse(dashBoardPage.verifyLogin());
    }

    @Test
    @Order(2)
    public void invalidUserAndPassword(){
        loginPage.userLogin("admin", "admin");
        Assert.assertFalse(dashBoardPage.verifyLogin());
    }

    @Order(3)
    @Test
    public void happyPathTest(){
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));  //global config class
        Assert.assertTrue(dashBoardPage.verifyLogin());
    }

}
