package pageObject;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BaseElement {
    private WebDriver driver;
    public HomePageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


}
