package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.Buffer;
import java.util.Date;
import java.util.logging.Logger;

public class Topic_05_Element {
    private static final Logger log = (Logger) LoggerFactory.getLogger(Topic_05_Element.class);
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01(){
        // Click vào Element dạng Button/ Checkbox/ Radio/ Link/ Image/...
        driver.findElement(By.xpath("")).click();

        // Nhập dữ liệu vào element cho phép edit (Editable) Textbox, TextArea, Editable Dropdown,...
        driver.findElement(By.xpath("")).click();
        driver.findElement(By.xpath("")).sendKeys();

        // Kiểm tra 1 element là enabled hay disabled
        // Áp dụng cho tất cả các Element
        Assert.assertTrue(driver.findElement(By.xpath("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("")).isEnabled());

        // Kiểm tra 1 Element là hiển thị hay không hiển thị
        // Hiển thị là có thể nhìn thấy trên UI + kích thước (Dài x Rộng) lớn hơn 0
        // Áp dụng cho tất cả các Element
        Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("")).isDisplayed());

        // Kiểm tra 1 Element có được chọn hay không
        Assert.assertTrue(driver.findElement(By.xpath("")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("")).isSelected());

        // Lấy ra dữ liệu dạng text ra của các Element và kiểm tra
        // Link/ Message/ Header/ Error Message/ Success Message/...
        Assert.assertEquals(driver.findElement(By.xpath("")).getText(),"");

        // Lấy ra giá trị thuộc tính HTML
        Assert.assertEquals(driver.findElement(By.xpath("")).getAttribute("class"),"");

        // Lấy ra giá trị thuộc tính HTML
        Assert.assertEquals(driver.findElement(By.xpath("")).getDomAttribute("class"),"");

        // Lấy giá trị thuộc tính trong DOM (Document Object Model)
        Assert.assertEquals(driver.findElement(By.xpath("")).getDomProperty("value"),"Automation");
        Assert.assertEquals(driver.findElement(By.xpath("")).getDomProperty("placeholder"),"");
        Assert.assertEquals(driver.findElement(By.xpath("")).getDomProperty("className"),"");

        // Lấy ra giá trị của CSS (ngôn ngữ FE)
        Assert.assertEquals(driver.findElement(By.xpath("")).getCssValue("placeholder"),"");

        Assert.assertEquals(driver.findElement(By.xpath("")).getAccessibleName(),"");
        Assert.assertEquals(driver.findElement(By.xpath("")).getAriaRole(),"");

        // Lấy ra chiều rộng x cao của 1 Element
        Dimension loginButtonSize = driver.findElement(By.xpath("")).getSize();
        loginButtonSize.getHeight();
        loginButtonSize.getWidth();

        // Lấy ra vị trí của Element so với màn hình
        Point loginButtonLocation = driver.findElement(By.xpath("")).getLocation();
        loginButtonLocation.getX();
        loginButtonLocation.getY();

        // Bao gồm cả Size và Location
        Rectangle loginButtonRect = driver.findElement(By.xpath("")).getRect();
        loginButtonRect.getHeight();
        loginButtonRect.getWidth();
        loginButtonRect.getX();
        loginButtonRect.getY();

        loginButtonSize = loginButtonRect.getDimension();
        loginButtonLocation = loginButtonRect.getPoint();

        // Lấy ra thẻ HTML của Element đấy
        String elementA = driver.findElement(By.xpath("")).getTagName();
        driver.findElement(By.xpath(elementA+ ""));

        // Shadow DOM
        driver.findElement(By.xpath("")).getShadowRoot();

        driver.findElement(By.xpath("")).submit();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
