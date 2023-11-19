package commons;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.ServerList;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SauceLabFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseTest{
    private WebDriver driver;

    protected final Logger log;

    protected BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String environmentName, String serverName, String browserName, String osName, String osVersion){
        switch (environmentName){
            case "local":
                driver = new LocalFactory(browserName).createDriver();
                break;
            case "browserStack":
                driver = new BrowserStackFactory(browserName, osName, osVersion).createDriver();
                break;
            case "sauceLab":
                driver = new SauceLabFactory(browserName, osName).createDriver();
                break;
            default:
                driver = new LocalFactory(browserName).createDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(getEnvironmentURL(serverName));
        return driver;
    }

    protected String getEnvironmentURL(String serverName){
        String serverURL = null;
        ServerList server = ServerList.valueOf(serverName.toUpperCase());
        switch (server){
            case TEST:
                serverURL = "http://dinhmanh.automation:86/orangehrm5/";
                break;
            case STAGING:
                serverURL = "https://opensource-demo.orangehrmlive.com/";
                break;
            case PRODUCT:
                serverURL = "https://www.orangehrm.com/";
                break;
            default:
                serverURL = "http://dinhmanh.automation:86/orangehrm5/";
                break;
        }
        return serverURL;
    }

    protected int generateFakeNumber(){
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    protected String getRandomEmail(){
        return "dinhmanh" + getRandomNumberByDateTime() + "@mail.com";
    }

    protected static long getRandomNumberByDateTime(){
        return Calendar.getInstance().getTimeInMillis();
    }

    protected void closeBrowserAndDriver(String serverName){
        if (serverName.equals("local")){
            String cmd = null;
            try {
                String osName = GlobalConstants.OS_NAME.toLowerCase();
                String driverInstanceName = driver.toString().toLowerCase();
                String browserDriverName = null;
                if (driverInstanceName.contains("chrome")) {
                    browserDriverName = "chromedriver";
                } else if (driverInstanceName.contains("internetexplorer")) {
                    browserDriverName = "IEDriverServer";
                } else if (driverInstanceName.contains("firefox")) {
                    browserDriverName = "geckodriver";
                } else if (driverInstanceName.contains("edge")) {
                    browserDriverName = "msedgedriver";
                } else if (driverInstanceName.contains("opera")) {
                    browserDriverName = "operadriver";
                } else {
                    browserDriverName = "safaridriver";
                }

                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
                } else {
                    cmd = "pkill " + browserDriverName;
                }

                if (driver != null) {
                    driver.manage().deleteAllCookies();
                    driver.quit();
                }
            } catch (Exception e){
                e.getMessage();
            }
            finally {
                try {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    private void deleteFileInReport(){
        deleteAllFileInFolder("allure-json");
    }

    private void deleteAllFileInFolder(String folderName){
        try {
            String pathFolder = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolder);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()){
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
