package account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.PIMPageObject;

public class Person_Information_Management extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private PIMPageObject pimPage;
    private String firstNameEmployee, middleNameEmployee, lastNameEmployee, userNameEmployee, passwordEmployee;
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        driver.get("http://dinhmanh.automation:86/orangehrm5/");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        username = "dinhmanh";
        password = "Dinhmanh1234@";
        firstNameEmployee = "Vladimir";
        middleNameEmployee = "Vladimirovich";
        lastNameEmployee = "Putin";
        userNameEmployee = "putin";
        passwordEmployee = "Putin123";
    }

    @Test
    public void TC_01_Login_Admin() {
        loginPage.inputToUserNameTextBox(username);
        loginPage.inputToPasswordTextBox(password);
        homePage = loginPage.clickToLoginButton();
        verifyTrue(homePage.isUserNameDisplay());
    }

    @Test
    public void TC_02_Add_New_Employee(){
        homePage.clickToMainMenuByText("PIM");
        pimPage = PageGeneratorManager.getPIMPage(driver);
        pimPage.clickAddEmployeeButton();
        pimPage.inputToEmployeeNameTextbox("First Name", firstNameEmployee);
        pimPage.inputToEmployeeNameTextbox("Middle Name", middleNameEmployee);
        pimPage.inputToEmployeeNameTextbox("Last Name", lastNameEmployee);
        pimPage.clickToCreateLoginDetailCheckbox();
        pimPage.inputToEmployeeLoginInforTextbox("Username", userNameEmployee);
        pimPage.inputToEmployeeLoginInforTextbox("Password", userNameEmployee);
        pimPage.inputToEmployeeLoginInforTextbox("Confirm Password", userNameEmployee);
        pimPage.clickToSaveButton();
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
