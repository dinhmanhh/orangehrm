package pageObject;

import commons.BaseElement;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageUIs.EmployeeListPageUI;
import pageUIs.PIMPageUI;
import pageUIs.PersonalDetailPageUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListPageObject extends PIMPageObject {
    private WebDriver driver;
    public EmployeeListPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public PersonalDetailObject clickToEditEmployeeByID(String employeeID) {
        waitForElementClickable(driver, EmployeeListPageUI.EDIT_EMPLOYEE_BUTTON_BY_ID, employeeID);
        clickToElement(driver, EmployeeListPageUI.EDIT_EMPLOYEE_BUTTON_BY_ID, employeeID);
        return PageGeneratorManager.getPersonalDetailPage(driver);
    }

    public PersonalDetailObject clickToDeleteEmployeeByID(String employeeID) {
        waitForElementClickable(driver, EmployeeListPageUI.DELETE_EMPLOYEE_BUTTON_BY_ID, employeeID);
        clickToElement(driver, EmployeeListPageUI.DELETE_EMPLOYEE_BUTTON_BY_ID, employeeID);
        return PageGeneratorManager.getPersonalDetailPage(driver);
    }

    public void clickToConfirmDeleteEmployee() {
        waitForElementClickable(driver, EmployeeListPageUI.CONFIRM_DELETE_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.CONFIRM_DELETE_EMPLOYEE_BUTTON);
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

    public void selectItemInIdSortDropdown(String textItem) {
        selectItemInCustomDropdown(driver, EmployeeListPageUI.ICON_SORT_BY_ID, EmployeeListPageUI.SORT_BY_ID_ITEM, textItem);
    }

    public boolean isEmployeeIDSortedByAsc() {
        List<WebElement> employeeID = getListWebElement(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXT_ITEM);
        List<String> employeeIDinUI = employeeID.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> employeeIDSorted = new ArrayList<>(employeeIDinUI);
        Collections.sort(employeeIDSorted);
        return employeeIDSorted.equals(employeeIDinUI);
    }

    public boolean isEmployeeIDSortedByDesc() {
        List<WebElement> employeeID = getListWebElement(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXT_ITEM);
        List<String> employeeIDatUI = employeeID.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> employeeIDSorted = new ArrayList<>(employeeIDatUI);
        Collections.sort(employeeIDSorted);
        Collections.reverse(employeeIDSorted);
        return employeeIDSorted.equals(employeeIDatUI);
    }

    public int getTotalEmployeeAtUI() {
        waitForElementVisible(driver, EmployeeListPageUI.TOTAL_EMPLOYEE_AT_UI_TEXT);
        String totalEmployeeText = getElementAttribute(driver, EmployeeListPageUI.TOTAL_EMPLOYEE_AT_UI_TEXT, "innerText");
        return Integer.parseInt(totalEmployeeText.replace("(", "").replace(")", "").replace(" Records Found", ""));
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
}
