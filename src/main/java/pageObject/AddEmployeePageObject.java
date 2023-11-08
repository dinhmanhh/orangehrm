package pageObject;

import commons.BaseElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;

public class AddEmployeePageObject extends PIMPageObject {
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @Step("Input to Employee Name textbox")
    public void inputToEmployeeNameTextbox(String textboxName, String value) {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_INFORMATION_TEXTBOX, textboxName);
        sendkeysToElement(driver, AddEmployeePageUI.EMPLOYEE_INFORMATION_TEXTBOX, value, textboxName);
    }

    public String getEmployeeIdAtUI() {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementAttribute(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    @Step("Click to Create Login Details button")
    public void clickToCreateLoginDetailCheckbox() {
        waitForElementClickable(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS_CHECKBOX);
        clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAILS_CHECKBOX);
    }

    @Step("Input to Employee Login Information textbox")
    public void inputToEmployeeLoginInforTextbox(String textboxName, String value) {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, textboxName);
        sendkeysToElement(driver, AddEmployeePageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, value, textboxName);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, AddEmployeePageUI.SAVE_EMPLOYEE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_EMPLOYEE_BUTTON);
    }
}
