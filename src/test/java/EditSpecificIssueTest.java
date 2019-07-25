import com.codecool.jiratest.tw3.BrowserFactory;
import com.codecool.jiratest.tw3.DashboardPage;
import com.codecool.jiratest.tw3.EditIssueSpecificPage;
import com.codecool.jiratest.tw3.LoginPage;
import com.codecool.jiratest.tw3.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditSpecificIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static DashboardPage dashBoardPage;
    private static EditIssueSpecificPage editIssueSpecificPage;

    @BeforeEach
    public void init() {
        driver = BrowserFactory.loadPage(System.getenv("driverType"), "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        editIssueSpecificPage = PageFactory.initElements(driver, EditIssueSpecificPage.class);
        navigate = new Navigate(driver);
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
        dashBoardPage.waitForDashboard();
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        editIssueSpecificPage.waitForEditButton();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void deleteRequiredFieldsTest() {
        editIssueSpecificPage.deleteRequiredFields();
        Assert.assertNotNull(editIssueSpecificPage.returnError());
        Assert.assertNotNull(editIssueSpecificPage.returnIssueType());
    }

    @Test
    public void deleteIssueSummaryTest() {
        editIssueSpecificPage.deleteIssueSummary();
        Assert.assertNotNull(editIssueSpecificPage.returnError());
    }

    @Test
    public void editDescriptionTest() {
        editIssueSpecificPage.navigateToDescriptionBox();
        String originalDescription = editIssueSpecificPage.returnText(editIssueSpecificPage.returnDescriptionValue());
        editIssueSpecificPage.editDescriptionBox(originalDescription + "1");
        Assert.assertEquals(originalDescription, editIssueSpecificPage.returnText(editIssueSpecificPage.returnDescriptionValue()));
    }

    /*@Test
    public void editIssueTypeTest() {
        editIssueSpecificPage.goToEditIssueType();
        String originalIssueType = editIssueSpecificPage.returnText(editIssueSpecificPage.returnIssueTypeText());
        editIssueSpecificPage.setIssueType("Task");
        String newIssueType = editIssueSpecificPage.returnText(editIssueSpecificPage.returnIssueTypeText());
        Assert.assertEquals("Task", newIssueType);
        editIssueSpecificPage.setPreviousIssueType(originalIssueType);
    }
     */
}