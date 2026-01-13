package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void TC_03_HTML_DropdownList1() throws InterruptedException {
        driver.get("https://www.facebook.com/r.php?entry_point=login");

        // Khởi tạo thư viện Select khi dropdown xổ xuống
        select = new Select(driver.findElement(By.cssSelector("select#month")));

        // Làm sao chọn được 1 tháng bất kỳ trong Month dropdown
        select.selectByVisibleText("Tháng 2");
        Thread.sleep(2000);

        // Chọn rồi làm sao để verify nó chọn thành công
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Tháng 2");

        // Làm sao để kiểm tra dropdown list có tổng cộng bao nhiêu item
        Assert.assertEquals(select.getOptions().size(),12);

        // Dropdown đấy có cho phép chọn nhiều hay ko
        Assert.assertFalse(select.isMultiple());

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
