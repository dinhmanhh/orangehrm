package pageObject;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }


    public boolean isUserNameDisplay() {
        waitForElementVisible(driver, HomePageUI.HEADER_USER_NAME);
        return isElementDisplayed(driver, HomePageUI.HEADER_USER_NAME);
    }

    public void clickToMainMenuByText(String mainMenu) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_MAIN_MENU, mainMenu);
        clickToElement(driver, HomePageUI.DYNAMIC_MAIN_MENU, mainMenu);
    }

}
