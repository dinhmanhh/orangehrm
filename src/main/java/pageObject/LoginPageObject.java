package pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void inputToUserNameTextBox(String username) {
        waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);
    }

    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

}
