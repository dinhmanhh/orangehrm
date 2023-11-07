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
import pageObject.PersonalDetailObject;

public class Person_Information_Management extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private PIMPageObject pimPage;
    private PersonalDetailObject personalDetailPage;
    private String firstNameEmployee, middleNameEmployee, lastNameEmployee, userNameEmployee, passwordEmployee;
    private String driverLicenseNumber, licenseExpiredDate, nationality, maritalStatus, dateOfBirth, gender;
    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowserDriver(browserName, environmentName);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        username = "dinhmanh";
        password = "Dinhmanh1234@";
        firstNameEmployee = "Vladimir";
        middleNameEmployee = "Vladimirovich";
        lastNameEmployee = "Putin";
        userNameEmployee = lastNameEmployee + getRandomNumberByDateTime();
        passwordEmployee = "Putin123@";

        driverLicenseNumber = "1406267";
        licenseExpiredDate = "2024-10-02";
        nationality = "Vietnamese";
        maritalStatus = "Married";
        dateOfBirth = "1997-04-29";
        gender = "Male";
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
        homePage.isPageLoadedSuccess(driver);
        homePage.clickToMainMenuByText("PIM");
        pimPage = PageGeneratorManager.getPIMPage(driver);

        pimPage.clickAddEmployeeButton();
        pimPage.inputToEmployeeNameTextbox("First Name", firstNameEmployee);
        pimPage.inputToEmployeeNameTextbox("Middle Name", middleNameEmployee);
        pimPage.inputToEmployeeNameTextbox("Last Name", lastNameEmployee);
        String employeeIdAtUI = pimPage.getEmployeeIdAtUI();
        pimPage.clickToCreateLoginDetailCheckbox();
        pimPage.inputToEmployeeLoginInforTextbox("Username", userNameEmployee);
        pimPage.inputToEmployeeLoginInforTextbox("Password", passwordEmployee);
        pimPage.inputToEmployeeLoginInforTextbox("Confirm Password", passwordEmployee);
        pimPage.clickToSaveButton();
        Assert.assertTrue(pimPage.isSavedSuccessMessageDisplayed());
        personalDetailPage = PageGeneratorManager.getPersonalDetailPage(driver);
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        Assert.assertTrue(personalDetailPage.isPersonalDetailPageDisplayed());
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("First Name"), firstNameEmployee);
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("Middle Name"), middleNameEmployee);
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("Last Name"), lastNameEmployee);
        String employeeIdAtDB = personalDetailPage.getEmployeeIdAtDB(employeeIdAtUI);
        verifyEquals(employeeIdAtUI, employeeIdAtDB);
    }

    @Test
    public void TC_03_Edit_Employee_Infor(){
        personalDetailPage.inputToDriverLicenseNumberTextbox(driverLicenseNumber);
        personalDetailPage.inputToLicenseExpiredDateTextbox(licenseExpiredDate);
        personalDetailPage.selectNationality(nationality);
        personalDetailPage.selectMaritalStatus(maritalStatus);
        personalDetailPage.inputToDateOfBirthTextbox(dateOfBirth);
        personalDetailPage.selectGenderByGenderText(gender);
        personalDetailPage.clickToSaveButton();
        personalDetailPage.isUpdatedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
