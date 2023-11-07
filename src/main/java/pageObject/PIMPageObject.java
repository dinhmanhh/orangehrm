package pageObject;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import pageUIs.PIMPageUI;
import utilities.MySQLConnUtils;
import utilities.MySQLTestConnection;

import java.sql.*;

public class PIMPageObject extends BaseElement {
    private WebDriver driver;
    public PIMPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @Step("Click to Add Employee button")
    public void clickAddEmployeeButton() {
        waitForElementClickable(driver, PIMPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, PIMPageUI.ADD_EMPLOYEE_BUTTON);
    }

    @Step("Input to Employee Name textbox")
    public void inputToEmployeeNameTextbox(String textboxName, String value) {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_INFORMATION_TEXTBOX, textboxName);
        sendkeysToElement(driver, PIMPageUI.EMPLOYEE_INFORMATION_TEXTBOX, value, textboxName);
    }

    public String getEmployeeIdAtUI() {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementAttribute(driver, PIMPageUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    @Step("Click to Create Login Details button")
    public void clickToCreateLoginDetailCheckbox() {
        waitForElementClickable(driver, PIMPageUI.CREATE_LOGIN_DETAILS_CHECKBOX);
        clickToElement(driver, PIMPageUI.CREATE_LOGIN_DETAILS_CHECKBOX);
    }

    @Step("Input to Employee Login Information textbox")
    public void inputToEmployeeLoginInforTextbox(String textboxName, String value) {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, textboxName);
        sendkeysToElement(driver, PIMPageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, value, textboxName);
    }

    @Step("Click to Save button")
    public void clickToSaveButton() {
        waitForElementClickable(driver, PIMPageUI.SAVE_EMPLOYEE_BUTTON);
        clickToElement(driver, PIMPageUI.SAVE_EMPLOYEE_BUTTON);
    }




}
