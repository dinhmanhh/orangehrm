package pageObject;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.PersonalDetailPageUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeIdAtDB;
    }

    public boolean isEmployeeDeletedFromDB(String employeeID) {
        Connection conn = MySQLConnUtils.getMySQLConnection();
        try {
            String sql = "SELECT employee_id FROM hs_hr_employee WHERE employee_id = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, employeeID);
            ResultSet rs = pstm.executeQuery();
            Assert.assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public int getTotalEmployeeAtDB() {
        Connection conn = MySQLConnUtils.getMySQLConnection();
        int totalEmployee = 0;
        String sql = "SELECT DISTINCT COUNT(*) as totalEmployee FROM hs_hr_employee;";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                totalEmployee = rs.getInt("totalEmployee");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalEmployee;
    }

    public void inputToDriverLicenseNumberTextbox(String driverLicenseNumber) {
        waitForAllElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        sendkeysToElement(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX, driverLicenseNumber);
    }

    public void inputToLicenseExpiryDateTextbox(String licenseExpiredDate) {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE);
        sendkeysToElement(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE, licenseExpiredDate);
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

    public void clickToSaveButtonAtPersonalInforArea() {
        waitForElementClickable(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_PERSONAL_INFOR_AREA);
        clickToElement(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_PERSONAL_INFOR_AREA);
    }

    public String getDriverLicenseNumber() {
        waitForElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX, "value");
    }

    public String getLicenseExpiryDate() {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE);
        return getElementAttribute(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE, "value");
    }

    public String getNationality() {
        waitForElementVisible(driver, PersonalDetailPageUI.NATIONALITY_DROPDOWN);
        return getElementAttribute(driver, PersonalDetailPageUI.NATIONALITY_DROPDOWN, "textContent");
    }

    public String getMaritalStatus() {
        waitForElementVisible(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN);
        return getElementAttribute(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN, "textContent");
    }

    public String getDateOfBirth() {
        waitForElementVisible(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
        return getElementAttribute(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX, "value");
    }

    public void uploadAttachmentFile(String fileName) {
        uploadMultipleFiles(driver, fileName);
    }

    public void clickToAddAttachmentButton() {
        waitForElementClickable(driver, PersonalDetailPageUI.ADD_ATTACHMENT_BUTTON);
        clickToElement(driver, PersonalDetailPageUI.ADD_ATTACHMENT_BUTTON);
    }

    public int getNumberOfImageIsUploaded() {
        waitForElementVisible(driver, PersonalDetailPageUI.IMAGE_UPLOADED_RESULT);
        return getElementSize(driver, PersonalDetailPageUI.IMAGE_UPLOADED_RESULT);
    }

    public void clickToSaveButtonAtUploadAttachmentArea() {
        waitForElementClickable(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_ATTACHMENT_AREA);
        clickToElement(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_ATTACHMENT_AREA);
    }

    public String getImageUploadedName() {
        waitForElementVisible(driver, PersonalDetailPageUI.IMAGE_UPLOADED_NAME_TEXT);
        return getElementAttribute(driver, PersonalDetailPageUI.IMAGE_UPLOADED_NAME_TEXT, "textContent");
    }

    public void clickToDeleteEmployeeByID(String employeeID) {
        waitForElementClickable(driver, PersonalDetailPageUI.DELETE_EMPLOYEE_BUTTON_BY_ID, employeeID);
        clickToElement(driver, PersonalDetailPageUI.DELETE_EMPLOYEE_BUTTON_BY_ID, employeeID);
    }

    public void clickToConfirmDeleteEmployee() {
        waitForElementClickable(driver, PersonalDetailPageUI.CONFIRM_DELETE_EMPLOYEE_BUTTON);
        clickToElement(driver, PersonalDetailPageUI.CONFIRM_DELETE_EMPLOYEE_BUTTON);
    }

    public void selectItemInIdSortDropdown(String textItem) {
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.ICON_SORT_BY_ID, PersonalDetailPageUI.SORT_BY_ID_ITEM, textItem);
    }

    public boolean isEmployeeIDSortedByAsc() {
        List<WebElement> employeeID = getListWebElement(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXT_ITEM);
        List<String> employeeIDinUI = employeeID.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> employeeIDSorted = new ArrayList<>(employeeIDinUI);
        Collections.sort(employeeIDSorted);
        return employeeIDSorted.equals(employeeIDinUI);
    }

    public boolean isEmployeeIDSortedByDesc() {
        List<WebElement> employeeID = getListWebElement(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXT_ITEM);
        List<String> employeeIDatUI = employeeID.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> employeeIDSorted = new ArrayList<>(employeeIDatUI);
        Collections.sort(employeeIDSorted);
        Collections.reverse(employeeIDSorted);
        return employeeIDSorted.equals(employeeIDatUI);
    }

    public int getTotalEmployeeAtUI() {
        waitForElementVisible(driver, PersonalDetailPageUI.TOTAL_EMPLOYEE_AT_UI_TEXT);
        String totalEmployeeText = getElementAttribute(driver, PersonalDetailPageUI.TOTAL_EMPLOYEE_AT_UI_TEXT, "innerText");
        return Integer.parseInt(totalEmployeeText.replace("(", "").replace(")", "").replace(" Records Found", ""));
    }
}
