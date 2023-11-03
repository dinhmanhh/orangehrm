package pageObject;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
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

    public boolean isAddNewEmployeeSuccessMessageDisplayed() {
        waitForElementVisible(driver, PIMPageUI.ADD_NEW_EMPLOYEE_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, PIMPageUI.ADD_NEW_EMPLOYEE_SUCCESS_MESSAGE);
    }

    public boolean isPersonalDetailPageDisplayed() {
        waitForElementUndisplayed(driver, BaseElementUI.SPINNER_LOADING_ICON);
        waitForElementVisible(driver, PIMPageUI.PERSONAL_DETAILS_LABEL);
        return isElementDisplayed(driver, PIMPageUI.PERSONAL_DETAILS_LABEL);
    }

    public String getEmployeeInforAtPersonalDetailsPage(String placeHolderName) {
        waitForElementVisible(driver, PIMPageUI.EMPLOYEE_INFOR_TEXTBOX_AT_PERSONAL_DETAILS_PAGE, placeHolderName);
        return getElementAttribute(driver, PIMPageUI.EMPLOYEE_INFOR_TEXTBOX_AT_PERSONAL_DETAILS_PAGE, "value", placeHolderName);
    }
}
