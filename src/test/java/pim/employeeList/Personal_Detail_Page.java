package pim.employeeList;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;

public class Personal_Detail_Page extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private PIMPageObject pimPage;
    private PersonalDetailObject personalDetailPage;
    private AddEmployeePageObject addEmployeePage;
    private String firstNameEmployee, middleNameEmployee, lastNameEmployee, userNameEmployee, passwordEmployee;
    private String driverLicenseNumber, licenseExpiryDate, nationality, maritalStatus, dateOfBirth, gender;
    private String fileUploadAtPersonalDetailPage;
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
        licenseExpiryDate = "2024-10-02";
        nationality = "Vietnamese";
        maritalStatus = "Married";
        dateOfBirth = "1997-04-29";
        gender = "Male";
        fileUploadAtPersonalDetailPage = "Java.png";

        loginPage.inputToUserNameTextBox(username);
        loginPage.inputToPasswordTextBox(password);
        homePage = loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isUserNameDisplayed());
        homePage.isPageLoadedSuccess(driver);

    }

    @Test
    public void TC_01_Add_New_Employee(){
        homePage.clickToMainMenuByText("PIM");
        pimPage = PageGeneratorManager.getPIMPage(driver);
        pimPage.clickToTopBarMenuByText("Add Employee");
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.inputToEmployeeNameTextbox("First Name", firstNameEmployee);
        addEmployeePage.inputToEmployeeNameTextbox("Middle Name", middleNameEmployee);
        addEmployeePage.inputToEmployeeNameTextbox("Last Name", lastNameEmployee);
        String employeeIdAtUI = addEmployeePage.getEmployeeIdAtUI();
        addEmployeePage.clickToCreateLoginDetailCheckbox();
        addEmployeePage.inputToEmployeeLoginInforTextbox("Username", userNameEmployee);
        addEmployeePage.inputToEmployeeLoginInforTextbox("Password", passwordEmployee);
        addEmployeePage.inputToEmployeeLoginInforTextbox("Confirm Password", passwordEmployee);
        addEmployeePage.clickToSaveButton();
        personalDetailPage = PageGeneratorManager.getPersonalDetailPage(driver);
        Assert.assertTrue(personalDetailPage.isSavedSuccessMessageDisplayed());
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        Assert.assertTrue(personalDetailPage.isPersonalDetailPageDisplayed());
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("First Name"), firstNameEmployee);
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("Middle Name"), middleNameEmployee);
        Assert.assertEquals(personalDetailPage.getEmployeeInforAtPersonalDetailsPage("Last Name"), lastNameEmployee);
        String employeeIdAtDB = personalDetailPage.getEmployeeIdAtDB(employeeIdAtUI);
        verifyEquals(employeeIdAtUI, employeeIdAtDB);
    }

    @Test
    public void TC_02_Edit_Employee_Infor(){
        personalDetailPage = PageGeneratorManager.getPersonalDetailPage(driver);
        personalDetailPage.inputToDriverLicenseNumberTextbox(driverLicenseNumber);
        personalDetailPage.inputToLicenseExpiryDateTextbox(licenseExpiryDate);
        personalDetailPage.selectNationality(nationality);
        personalDetailPage.selectMaritalStatus(maritalStatus);
        personalDetailPage.inputToDateOfBirthTextbox(dateOfBirth);
        personalDetailPage.selectGenderByGenderText(gender);
        personalDetailPage.clickToSaveButtonAtPersonalInforArea();
        personalDetailPage.isUpdatedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();

        personalDetailPage.clickToAddAttachmentButton();
        personalDetailPage.uploadAttachmentFile(fileUploadAtPersonalDetailPage);
        personalDetailPage.clickToSaveButtonAtUploadAttachmentArea();
        personalDetailPage.isSavedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();

        Assert.assertEquals(personalDetailPage.getDriverLicenseNumber(), driverLicenseNumber);
        Assert.assertEquals(personalDetailPage.getLicenseExpiryDate(), licenseExpiryDate);
        Assert.assertEquals(personalDetailPage.getNationality(), nationality);
        Assert.assertEquals(personalDetailPage.getMaritalStatus(), maritalStatus);
        Assert.assertEquals(personalDetailPage.getDateOfBirth(), dateOfBirth);
        Assert.assertEquals(personalDetailPage.getNumberOfImageIsUploaded(), 1);
        Assert.assertEquals(personalDetailPage.getImageUploadedName(), fileUploadAtPersonalDetailPage);
    }

    @Test
    public void TC_03_Delete_Employee(){
        personalDetailPage = PageGeneratorManager.getPersonalDetailPage(driver);
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.clickToDeleteEmployeeByID("0014");
        personalDetailPage.clickToConfirmDeleteEmployee();
        personalDetailPage.isDeletedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.isEmployeeDeletedFromDB("0014");

        int totalEmployeeAtUI = personalDetailPage.getTotalEmployeeAtUI();
        int totalEmployeeAtDB = personalDetailPage.getTotalEmployeeAtDB();
        System.out.println(totalEmployeeAtUI);
        System.out.println(totalEmployeeAtDB);
        Assert.assertEquals(totalEmployeeAtUI, totalEmployeeAtDB);
    }

    @Test
    public void TC_04_Sort_Employee(){
        personalDetailPage = PageGeneratorManager.getPersonalDetailPage(driver);

        personalDetailPage.selectItemInIdSortDropdown("Ascending");
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.isEmployeeIDSortedByAsc();
        personalDetailPage.sleepInSecond(1);

        personalDetailPage.selectItemInIdSortDropdown("Descending");
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.isEmployeeIDSortedByDesc();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
