package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchTestJava {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() throws Exception {
        // 1. Mở Google
        driver.get("https://www.google.com");

        // 2. Nhập từ khóa
        driver.findElement(By.name("q")).sendKeys("Selenium");

        // 3. Submit
        driver.findElement(By.name("q")).submit();

        Thread.sleep(2000); // demo, có thể thay bằng WebDriverWait

        // 4. Kiểm tra tiêu đề kết quả
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Selenium"),
                "Tiêu đề KHÔNG chứa chữ Selenium!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
