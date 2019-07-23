import com.codecool.jiratest.tw3.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BrowseProjectsTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static BrowseProjectsPage browseProjectsPage;
    private static Navigate navigate;

    @BeforeAll
    public static void init(){
        driver = BrowserFactory.loadPage(System.getenv("driverType"),"https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        browseProjectsPage = PageFactory.initElements(driver, BrowseProjectsPage.class);
        navigate = new Navigate(driver);
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

    @Test
    public void projectListAppears() {
        browseProjectsPage.getToProjectsFromDropdown();
        Assert.assertTrue(browseProjectsPage.verifyProjectListAppears());
    }

}
