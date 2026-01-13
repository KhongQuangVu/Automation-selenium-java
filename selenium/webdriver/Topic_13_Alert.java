package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_13_Alert {
    WebDriver driver;
    WebElement element;
    JavascriptExecutor js;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        Thread.sleep(2000);

        // Chờ cho Alert được presence
        // Presence: có xuất hiện trên HTML (Không bắt buộc phải xuất hiện trên UI)
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        // Thao tác với Alert
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        // Accept alert
        alert.accept();

        // Cancel Alert
        // alert.dismiss();
        // Lấy title/text của alert
        // alert.getText();
        // Nhập text
        // alert.sendKeys();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        // Accept alert
        //alert.accept();
        //Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Ok");

        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text ="Alert Prompt";
        alert.sendKeys(text);
        Thread.sleep(3000);
        // Accept alert
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);
    }

    @Test
    public void TC_04_Authentication_Alert() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/basic_auth");

        String user = "admin";
        String pass = "admin";
        String url = "the-internet.herokuapp.com/basic_auth";

        // Truy cập bằng cách ghép user:pass vào trước URL
        driver.get("http://" + user + ":" + pass + "@" + url);
        Thread.sleep(3000);

        // Verify message thành công
        String successMsg = driver.findElement(By.xpath("//p[contains(.,'Congratulations')]")).getText();
        Assert.assertTrue(successMsg.contains("Congratulations! You must have the proper credentials."));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
