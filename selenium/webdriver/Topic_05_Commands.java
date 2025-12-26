package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01(){
        // Các hàm tương tác với Browser thông qua biến driver
        //driver.

        // Các hàm tương tác với Element thông qua findElement
        // findElement đại diện cho Selenium WebElement
        // 1. Tương tác trực tiếp trên Element đó (Dùng 1 lần)
        driver.findElement(By.cssSelector("input#small-searchterm")).sendKeys("Samsung 26");

        // 2. Khai báo biến trước khi tương tác (Dùng nhiều lần)
        WebElement searchTextbox = driver.findElement(By.cssSelector("input#small-searchterm"));
        searchTextbox.clear();
        searchTextbox.sendKeys("Samsung Galaxy");

    }

    @Test
    public void TC_02(){

    }
    @AfterClass
    public void afterClass(){
       driver.quit();
    }
}
