package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;

public class BaseElement extends BasePage {
    private WebDriver driver;
    public BaseElement(WebDriver driver){
        this.driver = driver;
    }

    @Step("Check User Name In Header displayed")
    public boolean isUserNameDisplayed() {
        waitForElementVisible(driver, BaseElementUI.HEADER_USER_NAME);
        return isElementDisplayed(driver, BaseElementUI.HEADER_USER_NAME);
    }

    public void waitSpinnerLoadingIconUndisplayed() {
        waitForElementUndisplayed(driver, BaseElementUI.SPINNER_LOADING_ICON);
    }

    @Step("Click to Main menu")
    public void clickToMainMenuByText(String mainMenu) {
        waitSpinnerLoadingIconUndisplayed();
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_MAIN_MENU, mainMenu);
        clickToElement(driver, BaseElementUI.DYNAMIC_MAIN_MENU, mainMenu);
    }

    @Step("Check message Saved Success displayed")
    public boolean isSavedSuccessMessageDisplayed() {
        waitForElementVisible(driver, BaseElementUI.SAVED_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BaseElementUI.SAVED_SUCCESS_MESSAGE);
    }

    @Step("Check message Updated Success displayed")
    public boolean isUpdatedSuccessMessageDisplayed() {
        waitForElementVisible(driver, BaseElementUI.UPDATED_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BaseElementUI.UPDATED_SUCCESS_MESSAGE);
    }

    @Step("Check message Updated Success displayed")
    public boolean isDeletedSuccessMessageDisplayed() {
        waitForElementVisible(driver, BaseElementUI.DELETED_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BaseElementUI.DELETED_SUCCESS_MESSAGE);
    }



}
