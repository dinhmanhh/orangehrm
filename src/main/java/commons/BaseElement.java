package commons;

import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import pageUIs.HomePageUI;

public class BaseElement extends BasePage {
    private WebDriver driver;
    public BaseElement(WebDriver driver){
        this.driver = driver;
    }

    public boolean isUserNameDisplay() {
        waitForElementVisible(driver, BaseElementUI.HEADER_USER_NAME);
        return isElementDisplayed(driver, BaseElementUI.HEADER_USER_NAME);
    }

    public void clickToMainMenuByText(String mainMenu) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_MAIN_MENU, mainMenu);
        clickToElement(driver, BaseElementUI.DYNAMIC_MAIN_MENU, mainMenu);
    }


}
