package pim;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.*;

public class Personal_Detail_Page_Cloud_Testing extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    @Parameters({"environmentName", "serverName", "browserName", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String environmentName, @Optional("test") String serverName, @Optional("firefox") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion ) {
        driver = getBrowserDriver(environmentName, serverName, browserName, osName, osVersion);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        username = "Admin";
        password = "admin123";
    }

    @Test
    public void TC_01_Login(){
        loginPage.inputToUserNameTextBox(username);
        loginPage.inputToPasswordTextBox(password);
        homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isUserNameDisplayed());
        homePage.isPageLoadedSuccess(driver);
        Assert.assertTrue(homePage.isUserNameDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver("cloud");
    }
}
