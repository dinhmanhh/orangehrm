package pageObject;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.PIMPageUI;

public class PIMPageObject extends BaseElement {
    private WebDriver driver;
    public PIMPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void clickAddEmployeeButton() {
        waitForElementClickable(driver, PIMPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, PIMPageUI.ADD_EMPLOYEE_BUTTON);
    }

    public void inputToEmployeeNameTextbox(String textboxName, String value) {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_INFORMATION_TEXTBOX, textboxName);
        sendkeysToElement(driver, PIMPageUI.EMPLOYEE_INFORMATION_TEXTBOX, value, textboxName);
    }

    public void clickToCreateLoginDetailCheckbox() {
        waitForElementClickable(driver, PIMPageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
        clickToElement(driver, PIMPageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
    }

    public void inputToEmployeeLoginInforTextbox(String textboxName, String value) {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, textboxName);
        sendkeysToElement(driver, PIMPageUI.EMPLOYEE_LOGIN_INFOR_TEXTBOX, value, textboxName);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, PIMPageUI.SAVE_EMPLOYEE_BUTTON);
        clickToElement(driver, PIMPageUI.SAVE_EMPLOYEE_BUTTON);
    }
}
