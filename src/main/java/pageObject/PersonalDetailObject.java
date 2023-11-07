package pageObject;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import pageUIs.PIMPageUI;
import pageUIs.PersonalDetailPageUI;
import utilities.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDetailObject extends BaseElement {
    private WebDriver driver;

    public PersonalDetailObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Check Personal Detail page displayed")
    public boolean isPersonalDetailPageDisplayed() {
        waitForElementVisible(driver, PersonalDetailPageUI.PERSONAL_DETAILS_LABEL);
        return isElementDisplayed(driver, PersonalDetailPageUI.PERSONAL_DETAILS_LABEL);
    }

    public String getEmployeeInforAtPersonalDetailsPage(String placeHolderName) {
        waitForElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_INFOR_TEXTBOX, placeHolderName);
        return getElementAttribute(driver, PersonalDetailPageUI.EMPLOYEE_INFOR_TEXTBOX, "value", placeHolderName);
    }

    public String getEmployeeIdAtDB(String employeeID) {
        String employeeIdAtDB = "";
        Connection conn = MySQLConnUtils.getMySQLConnection();
        try {
            String sql = "SELECT employee_id FROM hs_hr_employee WHERE employee_id = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, employeeID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String employeeId = rs.getString("employee_id");
                employeeIdAtDB = employeeId;
            }
            return employeeIdAtDB;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void inputToDriverLicenseNumberTextbox(String driverLicenseNumber) {
        waitForAllElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        sendkeysToElement(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX, driverLicenseNumber);
    }

    public void inputToLicenseExpiredDateTextbox(String licenseExpiredDate) {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRED_DATE);
        sendkeysToElement(driver, PersonalDetailPageUI.LICENSE_EXPIRED_DATE, licenseExpiredDate);
    }

    public void selectNationality(String nationality) {
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.NATIONALITY_DROPDOWN, PersonalDetailPageUI.CUSTOM_DROPDOWN, nationality);
    }

    public void selectMaritalStatus(String maritalStatus) {
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN, PersonalDetailPageUI.CUSTOM_DROPDOWN, maritalStatus);
    }

    public void inputToDateOfBirthTextbox(String dateOfBirth) {
        waitForElementVisible(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
        sendkeysToElement(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }

    public void selectGenderByGenderText(String gender) {
        waitForElementClickable(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
        clickToElement(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, PersonalDetailPageUI.SAVE_BUTTON);
        clickToElement(driver, PersonalDetailPageUI.SAVE_BUTTON);
    }
}
