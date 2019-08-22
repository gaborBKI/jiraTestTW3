import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.utility.CapabilityLoader;
import com.codecool.jiratest.tw3.utility.Navigate;
import com.codecool.jiratest.tw3.utility.Util;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
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
        Navigate navigate = new Navigate(driver);
        navigate.toPage(baseUrl);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
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
        loginPage.userLogin(System.getProperty("userName"), System.getProperty("passWord"));  //global config class
        Assert.assertTrue(dashBoardPage.verifyLogin());
    }

}
