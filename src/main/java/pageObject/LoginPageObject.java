package pageObject;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Input to Username textbox")
    public void inputToUserNameTextBox(String username) {
        waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);
    }

    @Step("Input to Password textbox")
    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click to Login button")
    public HomePageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }

}
