package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.IContainer;

import java.time.Duration;

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

        // Url cu page hiện tại
        Assert.assertEquals(driver.getCurrentUrl(),"");

        // HTML source code của page hiện tại
        Assert.assertTrue(driver.getPageSource().contains(""));

        // Trả về Title của page hiện tại
        driver.getTitle();

        // Trả về ID của Tab/Window hiện tại của Active
        driver.getWindowHandle();

        // Trả về ID của tất cả Tab/Window hiện tại của Active
        driver.getWindowHandles();

        // Mở rộng/thu nhỏ kích thước trang web
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Mở full màn hình/ full viền
        driver.manage().window().fullscreen();

        // Test GUI (Giao diện) = Size, Font, Color, Position,...
        // Set kích thước trình duyệt
        // Test Manual 1 app có nhiều kích thước
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Lấy ra kích thước của Browser là bao nhiêu
        driver.manage().window().getSize();

        // Set màn hình ở vị trí nào so với độ phân giải màn hình hiện tại
        driver.manage().window().setPosition(new Point(0,100));

        // Lấy tọa độ của Browser đang đứng
        driver.manage().window().getPosition();

        // Set cho việt tìm kiếm element (findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.manage().timeouts().getImplicitWaitTimeout();

        // Áp dụng cho JavaScriptExecutor
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getScriptTimeout();

        // Áp dụng cho page được load tối da bao nhiêu
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getPageLoadTimeout();

        // Lấy ra Cookies theo tên/ xóa cookies/ xóa toàn bộ
        driver.manage().getCookies();

        // Tương tự driver.get("");
        // driver.navigate() support tốt hơn cho việc truy vết/ phân tích lịch sử duyệt Web
        driver.navigate().to("http://live.techpanda.org/");

        driver.navigate().back();

        driver.navigate().forward();

        driver.navigate().refresh();

        // Alert
        // Window
        // Frame
        driver.switchTo().alert();

        driver.switchTo().window("");

        driver.switchTo().frame("");
    }

    @Test
    public void TC_02(){

    }
    @AfterClass
    public void afterClass(){
       driver.quit();
    }
}
