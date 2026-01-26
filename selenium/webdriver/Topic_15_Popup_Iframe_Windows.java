package webdriver;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Topic_15_Popup_Iframe_Windows {
    WebElement element;
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void beforeClass(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        // Tắt bảng "Change your password" và các tính năng liên quan đến mật khẩu (bên phải hình bạn gửi)
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Chặn trình quản lý mật khẩu đề xuất lưu/đổi mật khẩu
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Vô hiệu hóa tính năng bảo mật kiểm tra mật khẩu bị rò rỉ (Safe Browsing)
        prefs.put("safebrowsing.enabled", false);

        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Popup() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(1000); // Chờ popup load

        // Step 03: Kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());

        // Step 04 & 05: Nhập thông tin
        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("automationfc");

        // Step 06: Click Đăng nhập & Verify message
        driver.findElement(By.xpath("//button[@type='submit' and text()='Đăng nhập']")).click();
        Thread.sleep(1000);
        String errorMsg = driver.findElement(By.xpath("//div[@class='SnackbarItem-message']")).getText();
        Assert.assertEquals(errorMsg, "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        // Step 07: Đóng popup
        driver.findElement(By.xpath("//button[contains(@class,'close-btn')]")).click();
        Thread.sleep(1000);

        // Step 08: Kiểm tra popup đã đóng
        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);
    }

    @Test
    public void TC_02_Popup(){
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        driver.findElement(By.id("user-login")).clear();
        driver.findElement(By.id("user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("user-password")).clear();
        driver.findElement(By.id("user-password")).sendKeys("123456");

        driver.findElement(By.id("btn-submit-login")).click();

        String errorMessage = driver.findElement(By.xpath("//div[@class='t-text-error' and contains(text(),'Sai tên')]")).getText();
        Assert.assertEquals(errorMessage, "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Popup_notDom(){
        driver.get("https://tiki.vn/");

        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Tài khoản')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sc-2745a82-0 gvDbCz']")).isDisplayed());

        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Email không được để trống')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Mật khẩu không được để trống')]")).isDisplayed());

        driver.findElement(By.xpath("//button[@class='btn-close']")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class,'sc-2745a82-0')]")).size(), 0);
    }

    @Test
    public void TC_04_Random_Popup(){
        driver.get("http://www.kmplayer.com/");

        By popup = By.xpath("//div[@role='dialog']");
        if (driver.findElements(popup).size() > 0){
            driver.findElement(By.xpath("//span[@class='notranslate']")).click();
        }
        driver.findElement(By.xpath("//div[@class='close']")).click();
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
