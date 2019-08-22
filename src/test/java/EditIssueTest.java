import com.codecool.jiratest.tw3.env.Creds;
import com.codecool.jiratest.tw3.pages.*;
import com.codecool.jiratest.tw3.utility.BrowserFactory;
import com.codecool.jiratest.tw3.utility.CapabilityLoader;
import com.codecool.jiratest.tw3.utility.Navigate;
import com.codecool.jiratest.tw3.utility.Util;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class EditIssueTest {

    private static WebDriver driver;
    private static Navigate navigate;
    private static LoginPage loginPage;
    private static EditIssuePage objEditIssuePage;
    private static DashboardPage dashboardPage;
    private static CreateIssuePage objCreateIssuePage;
    private static String baseUrl;
    private static String nodeURL;

    @BeforeEach
    public void init() throws MalformedURLException {
        DesiredCapabilities capability = CapabilityLoader.setCapability();
        baseUrl = Util.getBaseURL();
        nodeURL = "http://10.44.1.192:5566/wd/hub";
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        objEditIssuePage = PageFactory.initElements(driver, EditIssuePage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        objCreateIssuePage = PageFactory.initElements(driver, CreateIssuePage.class);
        navigate = new Navigate(driver);
        navigate.toPage(baseUrl);
        loginPage.userLogin(Creds.USERNAME.getValue(), Creds.PASSWORD.getValue());
        dashboardPage.waitForDashboard();
    }

    @ParameterizedTest  //csv file issue ids only
    @CsvFileSource(resources = "/urlList.csv", numLinesToSkip = 1)  //base url required
    public void editPageOpensTest(String url) {
        driver.navigate().to(url);
        objEditIssuePage.clickEdit();
        Assert.assertTrue(objEditIssuePage.verifyEditButton());   // verify if button exists
    }

    @Test
    public void inlineEditing() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/COALA-1");
        String originalIssueName = objEditIssuePage.getSummaryText();
        String newIssueName = originalIssueName + " --- test ---";
        objEditIssuePage.editSummaryField(newIssueName);
        String modifiedIssueName = objEditIssuePage.getSummaryText();
        Assert.assertEquals(newIssueName, modifiedIssueName);
        objEditIssuePage.editSummaryField(originalIssueName);
    }

    /*
    @Test
    public void inlineEditingWithCreatingNewIssue() {
        objDashboardPage.clickToCreateIssue();
        objCreateIssuePage.fillSummaryFieldWhenCreatingIssue("My test issue");
        objDashboardPage.catchCreatePopUpWindow();
        String newSummary = "NEW TEST NAME";
        objEditIssuePage.editSummaryField(newSummary);
        String modifiedSummary = objEditIssuePage.getSummaryText();
        Assert.assertEquals(newSummary, modifiedSummary);
        objEditIssuePage.deleteThisIssue();
    }
     */

    @Test
    public void deleteRequiredFieldsTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditIssuePage.waitForEditButton();
        objEditIssuePage.deleteRequiredFields();
        Assert.assertNotNull(objEditIssuePage.returnError());
        Assert.assertNotNull(objEditIssuePage.returnIssueType());
    }

    @Test
    public void deleteIssueSummaryTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditIssuePage.waitForEditButton();
        // Need to insert a wait for summary
        objEditIssuePage.deleteIssueSummary();
        Assert.assertNotNull(objEditIssuePage.returnError());
    }

    @Test
    public void editDescriptionTest() {
        navigate.toPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        objEditIssuePage.waitForEditButton();
        objEditIssuePage.navigateToDescriptionBox();
        String originalDescription = objEditIssuePage.returnText(objEditIssuePage.returnDescriptionValue());
        objEditIssuePage.editDescriptionBox(originalDescription + "1");
        Assert.assertEquals(originalDescription, objEditIssuePage.returnText(objEditIssuePage.returnDescriptionValue()));
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @AfterAll
    public static void destroy(){
        driver.quit();
    }

}
