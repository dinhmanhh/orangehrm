package account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
        userNameEmployee = lastNameEmployee + getRandomNumberByDateTime();
        passwordEmployee = "Putin123@";
    }

    @Test
    public void TC_01_Login_Admin() {
        loginPage.inputToUserNameTextBox(username);
        loginPage.inputToPasswordTextBox(password);
        homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isUserNameDisplayed());
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
        pimPage.inputToEmployeeLoginInforTextbox("Password", passwordEmployee);
        pimPage.inputToEmployeeLoginInforTextbox("Confirm Password", passwordEmployee);
        pimPage.clickToSaveButton();
        Assert.assertTrue(pimPage.isAddNewEmployeeSuccessMessageDisplayed());
        Assert.assertTrue(pimPage.isPersonalDetailPageDisplayed());
        Assert.assertEquals(pimPage.getEmployeeInforAtPersonalDetailsPage("First Name"), firstNameEmployee);
        Assert.assertEquals(pimPage.getEmployeeInforAtPersonalDetailsPage("Middle Name"), middleNameEmployee);
        Assert.assertEquals(pimPage.getEmployeeInforAtPersonalDetailsPage("Last Name"), lastNameEmployee);
    }

    @Test
    public void TC_03_Edit_Employee_Infor(){

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
