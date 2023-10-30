package account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.LoginPageObject;

public class Login extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        username = "Admin";
        password = "admin123";
    }

    @Test
    public void Login() {
        loginPage.inputToUserNameTextBox(username);
        loginPage.inputToPasswordTextBox(password);
        loginPage.clickToLoginButton();
        loginPage.sleepInSecond(3);
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
