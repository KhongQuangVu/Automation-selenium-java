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

import java.nio.file.WatchEvent;
import java.time.Duration;

public class Play_audio_vang_lai {
    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC1_Player() throws InterruptedException {
        driver.get("https://mydio.vn/");

        driver.findElement(By.xpath("//button[text()='Để sau']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(@class,'text-lg-18-21') and contains(text(),'Sách mới')]")
        ));

        // scroll vào giữa màn hình
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element
        );
        // Mở chuyên mục thể loại
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        driver.findElement(By.xpath("(//div[@class='relative block postcast-item overflow-hidden'])[1]")).click();
        Thread.sleep(3000);
        String nameAudio = driver.findElement(By.xpath("//p[@id='audio-label']")).getText();
        System.out.println(nameAudio);
        Thread.sleep(3000);

        // full player
        Assert.assertTrue(driver.findElement(By.xpath("//button[.//img[@alt='icon-zoom-out-player']]")).isEnabled());
        driver.findElement(By.xpath("//button[.//img[@alt='icon-zoom-out-player']]")).click();
        String chuongPhat = driver.findElement(By.xpath("//span[@class='t-ellipsis-2 mt-2 2xl:mt-3 text-center text-black-128']")).getText();
        System.out.println(chuongPhat);
    }

    @Test
    public void TC_02_Player(){
        driver.get("https://mydio.vn/");

        driver.findElement(By.xpath("//button[text()='Để sau']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[contains(@class,'text-lg-18-21') and contains(text(),'Sách mới')]")
        ));

        // scroll vào giữa màn hình
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element
        );
        driver.findElement(By.xpath("(//div[@class='w-48 h-72 xl:w-72 xl:h-108 inline-block float-left'])[1]")).click();
        String nameaudioBookDetail = driver.findElement(By.xpath("//h1[@class='book-title line-clamp-2 mb-4 t-ellipsis-2']")).getText();
        System.out.println(nameaudioBookDetail);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
