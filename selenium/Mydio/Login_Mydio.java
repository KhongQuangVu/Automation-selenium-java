package Mydio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_Mydio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_login_Mydio() throws InterruptedException {
        driver.get("https://mydio.vn/");

        driver.findElement(By.xpath("//button[text()='đăng nhập']")).click();
        String username = "0368579644";
        String password = "370194";

        driver.findElement(By.xpath("(//input[@autocomplete='new-password'])[1]")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Nghe gần đây']")).isDisplayed());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(@class,'text-lg-18-21') and contains(text(),'Nghe gần đây')]")
        ));

        // scroll vào giữa màn hình
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element
        );

        // đợi clickable rồi click
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[contains(@class,'rounded-xl w-full')])[1]")).click();
        Thread.sleep(3000);

        String nameAudio = driver.findElement(By.xpath("//h1[@class='book-title line-clamp-2 mb-4 t-ellipsis-2']")).getText();
        // Assert.assertEquals();
    }

    @Test
    public void TC_02_Login_Fail(){
        driver.get("https://mydio.vn/");

        driver.findElement(By.xpath("//button[text()='đăng nhập']")).click();
        String username = "0368579644";
        String password = "370190";

        driver.findElement(By.xpath("(//input[@autocomplete='new-password'])[1]")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        String toastMess = driver.findElement(By.xpath("//h2[@class='el-notification__title']")).getText();
        System.out.println(toastMess);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='el-notification__title']")).isDisplayed());
        Assert.assertEquals(toastMess,"Thông tin đăng nhập không đúng. Xin Quý khách vui lòng thao tác lại.");
    }

    @Test
    public void TC_03_Login_Fail_Captcha(){
        driver.get("https://mydio.vn/");

        driver.findElement(By.xpath("//button[text()='đăng nhập']")).click();
        String username = "0368579644";
        String password = "370190";

        driver.findElement(By.xpath("(//input[@autocomplete='new-password'])[1]")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        String toastMess = driver.findElement(By.xpath("//h2[@class='el-notification__title']")).getText();
        System.out.println(toastMess);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='el-notification__title']")).isDisplayed());
        Assert.assertEquals(toastMess,"Yêu cầu nhập mã xác thực.");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
