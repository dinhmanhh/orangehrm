package pageObject;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BaseElement {
    private WebDriver driver;
    public HomePageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


}
