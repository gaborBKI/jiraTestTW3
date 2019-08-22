import com.codecool.jiratest.tw3.env.Creds;
import com.codecool.jiratest.tw3.pages.DashboardPage;
import com.codecool.jiratest.tw3.pages.LoginPage;
import com.codecool.jiratest.tw3.pages.ReLoginPage;
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
public class LogoutTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
    private static ReLoginPage reLoginPage;
    private static String baseUrl;
    private static String nodeURL;

    @BeforeAll
    public static void init() throws MalformedURLException {
        DesiredCapabilities capability = CapabilityLoader.setCapability();
        baseUrl = Util.getBaseURL();
        nodeURL = "http://10.44.1.192:5566/wd/hub";
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        reLoginPage = PageFactory.initElements(driver, ReLoginPage.class);
        navigate = new Navigate(driver);
    }

    @BeforeEach
    public void navigate(){
        navigate.toPage(baseUrl);
        loginPage.userLogin(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
        dashBoardPage.logOut();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
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
        reLoginPage.reLogIn(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
        Assert.assertTrue(dashBoardPage.verifyLogin());
    }

}
