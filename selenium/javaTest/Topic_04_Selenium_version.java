package javaTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Selenium_version {
    WebDriver driver;

    @Test
    public void TC_01_Selenium_Latest(){
        // Selenium 4.10 trở lên
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.quit();

        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.quit();

        driver = new EdgeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.quit();
    }

    @Test
    public void TC_02_Selenium_3x(){
        // Selenium 3x trở xuống (3x/2x/1x)

    }
}
