import com.codecool.jiratest.tw3.pages.*;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.Navigate;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class EditIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static EditProjectPage objEditProjectPage;
    private static DashboardPage dashboardPage;
    private static CreateIssuePage objCreateIssuePage;

    @BeforeEach
    public void init() {
        driver = BrowserFactory.loadPage(System.getenv("driverType"));
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        objEditProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        objCreateIssuePage = PageFactory.initElements(driver, CreateIssuePage.class);
        navigate = new Navigate(driver);
        navigate.toPage(System.getenv("LOGIN_PAGE"));
        loginPage.userLogin(System.getenv("JIRAUSER"), System.getenv("PASSWORD"));
        dashboardPage.waitForDashboard();
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
        String originalIssueName = objEditProjectPage.getSummaryText();
        String newIssueName = originalIssueName + " --- test ---";
        objEditProjectPage.editSummaryField(newIssueName);
        String modifiedIssueName = objEditProjectPage.getSummaryText();
        Assert.assertEquals(newIssueName, modifiedIssueName);
        objEditProjectPage.editSummaryField(originalIssueName);
    }

    /*
    @Test
    public void inlineEditingWithCreatingNewIssue() {
        objDashboardPage.clickToCreateIssue();
        objCreateIssuePage.fillSummaryFieldWhenCreatingIssue("My test issue");
        objDashboardPage.catchCreatePopUpWindow();
        String newSummary = "NEW TEST NAME";
        objEditProjectPage.editSummaryField(newSummary);
        String modifiedSummary = objEditProjectPage.getSummaryText();
        Assert.assertEquals(newSummary, modifiedSummary);
        objEditProjectPage.deleteThisIssue();
    }
     */

    @Test
    public void deleteRequiredFieldsTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditProjectPage.waitForEditButton();
        objEditProjectPage.deleteRequiredFields();
        Assert.assertNotNull(objEditProjectPage.returnError());
        Assert.assertNotNull(objEditProjectPage.returnIssueType());
    }

    @Test
    public void deleteIssueSummaryTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditProjectPage.waitForEditButton();
        objEditProjectPage.deleteIssueSummary();
        Assert.assertNotNull(objEditProjectPage.returnError());
    }

    @Test
    public void editDescriptionTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditProjectPage.waitForEditButton();
        objEditProjectPage.navigateToDescriptionBox();
        String originalDescription = objEditProjectPage.returnText(objEditProjectPage.returnDescriptionValue());
        objEditProjectPage.editDescriptionBox(originalDescription + "1");
        Assert.assertEquals(originalDescription, objEditProjectPage.returnText(objEditProjectPage.returnDescriptionValue()));
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

}
