package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.List;

public class Topic_18_Upload_file {
    WebDriver driver;

    // Lấy đường dẫn tương đối
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\";

    WebElement element;
    String mountainFile = "mountain.jpg";
    String flowerFile = "flower.jpg";

    String mountainFilePath = uploadFilePath + mountainFile;
    String flowerFilePath = uploadFilePath + flowerFile;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver. manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_singer_file() throws InterruptedException {
        // File để ở đâu
        // File cố định trên máy => qua máy khác ko tìm được
        // => Bất kỳ máy nào cũng chạy dc
        // => Để file trong chính source code
        // => Lấy đường dẫn tương đối
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy = By.cssSelector("input[type='file']");

        // Load file
        driver.findElement(uploadFileBy).sendKeys(mountainFilePath);
        driver.findElement(uploadFileBy).sendKeys(flowerFilePath);

        // Upload File
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton : startUploadButtons){
            startButton.click();
            Thread.sleep(1000);
        }

        // Verify Upload
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mountainFile +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ flowerFile +"']")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
