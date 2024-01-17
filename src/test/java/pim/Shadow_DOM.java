package pim;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = WebDriverManager.firefoxdriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://shopee.vn/");

    }

    @Test
    public void TC_01_Shopee(){
        WebElement shadowDOM1 = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowDOM1Context = shadowDOM1.getShadowRoot();

        WebElement shadowDOM2 = shadowDOM1Context.findElement(By.cssSelector("div.home-popup__content>shopee-banner-simple"));
        SearchContext shadowDOM2Context = shadowDOM2.getShadowRoot();

        if (shadowDOM2Context.findElement(By.cssSelector("img.banner-image")).isDisplayed()){
            shadowDOM1Context.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        }


    }



    @AfterClass
    public void afterClass(){
        driver.quit();

    }
}
