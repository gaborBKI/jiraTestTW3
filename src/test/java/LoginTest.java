import com.codecool.jiratest.tw3.DashboardPage;
import com.codecool.jiratest.tw3.LoginPage;
import com.codecool.jiratest.tw3.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;

    @BeforeAll
    public static void init(){
        switch (System.getenv("driverType")){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
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
        Assert.assertTrue(dashBoardPage.verifyUserButton());
    }




}
