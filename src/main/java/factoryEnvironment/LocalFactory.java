package factoryEnvironment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocalFactory {
    private WebDriver driver;
    private String browserName;


    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.FIREFOX) {
            driver = WebDriverManager.firefoxdriver().create();
        } else if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            driver = new ChromeDriver(options);
        }  else if (browser == Browser.EDGE) {
            driver = WebDriverManager.edgedriver().create();
        } else if (browserName.equals("safari")) {
            driver = WebDriverManager.safaridriver().create();
        } else if (browserName.equals("opera")) {
            driver = WebDriverManager.operadriver().create();
        } else {
            throw new RuntimeException("Browser name invalid!");
        }
        return driver;
    }
}
