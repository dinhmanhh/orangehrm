package pim;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.*;

public class Personal_Detail_Page extends BaseTest {
    private WebDriver driver;
    private String username, password;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private PIMPageObject pimPage;
    private PersonalDetailObject personalDetailPage;
    private AddEmployeePageObject addEmployeePage;
    private EmployeeListPageObject employeeListPage;
    private String employeeIdAtUI;
    private String firstNameEmployee, middleNameEmployee, lastNameEmployee, userNameEmployee, passwordEmployee;
    private String driverLicenseNumber, licenseExpiryDate, nationality, maritalStatus, dateOfBirth, gender;
    private String fileUploadAtPersonalDetailPage;
    @Parameters({"environmentName", "serverName", "browserName", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String environmentName, @Optional("test") String serverName, @Optional("firefox") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion ) {
        driver = getBrowserDriver(environmentName, serverName, browserName, osName, osVersion);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        username = "dinhmanh";
        password = "Dinhmanh1234@";
        firstNameEmployee = "First";
        middleNameEmployee = "Middle";
        lastNameEmployee = "Last";
        userNameEmployee = getRandomEmail();
        passwordEmployee = "Employee1234@";

        driverLicenseNumber = "1406267";
        licenseExpiryDate = "2024-10-02";
        nationality = "American";
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
        employeeIdAtUI = addEmployeePage.getEmployeeIdAtUI();
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
        Assert.assertEquals(employeeIdAtUI, employeeIdAtDB);
    }

    @Test
    public void TC_02_Edit_Employee_Infor(){
        personalDetailPage.clickToTopBarMenuByText("Employee List");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToEditEmployeeByID(employeeIdAtUI);
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.inputToDriverLicenseNumberTextbox(driverLicenseNumber);
        personalDetailPage.inputToLicenseExpiryDateTextbox(licenseExpiryDate);
        personalDetailPage.selectNationality(nationality);
        personalDetailPage.selectMaritalStatus(maritalStatus);
        personalDetailPage.inputToDateOfBirthTextbox(dateOfBirth);
        personalDetailPage.selectGenderByGenderText(gender);
        personalDetailPage.clickToSaveButtonAtPersonalInforArea();
        personalDetailPage.isUpdatedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        Assert.assertEquals(personalDetailPage.getDriverLicenseNumber(), driverLicenseNumber);
        Assert.assertEquals(personalDetailPage.getLicenseExpiryDate(), licenseExpiryDate);
        Assert.assertEquals(personalDetailPage.getNationality(), nationality);
        Assert.assertEquals(personalDetailPage.getMaritalStatus(), maritalStatus);
        Assert.assertEquals(personalDetailPage.getDateOfBirth(), dateOfBirth);

        personalDetailPage.clickToTopBarMenuByText("Employee List");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToEditEmployeeByID(employeeIdAtUI);
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        personalDetailPage.clickToAddAttachmentButton();
        personalDetailPage.uploadAttachmentFile(fileUploadAtPersonalDetailPage);
        personalDetailPage.clickToSaveButtonAtUploadAttachmentArea();
        personalDetailPage.isSavedSuccessMessageDisplayed();
        personalDetailPage.waitSpinnerLoadingIconUndisplayed();
        Assert.assertEquals(personalDetailPage.getNumberOfImageIsUploaded(), 1);
        Assert.assertEquals(personalDetailPage.getImageUploadedName(), fileUploadAtPersonalDetailPage);
    }

    @Test
    public void TC_03_Delete_Employee(){
        employeeListPage.clickToTopBarMenuByText("Employee List");
        employeeListPage.waitSpinnerLoadingIconUndisplayed();

        employeeListPage.clickToDeleteEmployeeByID(employeeIdAtUI);
        employeeListPage.clickToConfirmDeleteEmployee();
        employeeListPage.isDeletedSuccessMessageDisplayed();
        employeeListPage.waitSpinnerLoadingIconUndisplayed();
        employeeListPage.isEmployeeDeletedFromDB(employeeIdAtUI);

        int totalEmployeeAtUI = employeeListPage.getTotalEmployeeAtUI();
        int totalEmployeeAtDB = employeeListPage.getTotalEmployeeAtDB();
        Assert.assertEquals(totalEmployeeAtUI, totalEmployeeAtDB);
    }

    @Test
    public void TC_04_Sort_Employee(){
        employeeListPage.clickToTopBarMenuByText("Employee List");
        employeeListPage.waitSpinnerLoadingIconUndisplayed();

        employeeListPage.selectItemInIdSortDropdown("Ascending");
        employeeListPage.waitSpinnerLoadingIconUndisplayed();
        employeeListPage.isEmployeeIDSortedByAsc();
        employeeListPage.sleepInSecond(1);

        employeeListPage.selectItemInIdSortDropdown("Descending");
        employeeListPage.waitSpinnerLoadingIconUndisplayed();
        employeeListPage.isEmployeeIDSortedByDesc();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver("local");
    }
}
