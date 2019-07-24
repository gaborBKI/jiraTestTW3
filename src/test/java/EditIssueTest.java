import com.codecool.jiratest.tw3.EditProjectPage;
import com.codecool.jiratest.tw3.LoginPage;
import com.codecool.jiratest.tw3.Navigate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;

public class EditIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static EditProjectPage objEditProjectPage;


    @BeforeAll
    public static void setUp(){
        switch (System.getenv("driverType")){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        objEditProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        navigate = new Navigate(driver);
        navigate.toLoginPage();
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/urlList.csv", numLinesToSkip = 1)
    public void editPageOpensTest(String url) {
        driver.navigate().to(url);
        objEditProjectPage.clickEdit();
        Assert.assertTrue(objEditProjectPage.verifyEditButton());
    }

    @Test
    public void inlineEditing() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/COALA-1");
        String newSummary = "Coala Task 3";
        objEditProjectPage.editSummaryField(newSummary);
        String modifiedSummary = objEditProjectPage.modifiedSummaryText();
        Assert.assertEquals(newSummary, modifiedSummary);
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

}
