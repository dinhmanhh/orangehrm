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

    public void clickToTopBarMenuByText(String topbarMenu) {
        waitForElementVisible(driver, PIMPageUI.TOP_BAR_MENU, topbarMenu);
        clickToElement(driver, PIMPageUI.TOP_BAR_MENU, topbarMenu);
    }
}
