package commons;

import org.openqa.selenium.WebDriver;
import pageObject.*;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }
    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
    public static PIMPageObject getPIMPage(WebDriver driver){
        return new PIMPageObject(driver);
    }
    public static PersonalDetailObject getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailObject(driver);
    }
    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }
}
