package webdriver;

import org.bouncycastle.asn1.dvcs.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_19_Wait {
    WebDriver driver;
    WebElement element;
    WebDriverWait explicitwait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        explicitwait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Điều kiện để check 1 element
    // Điều kiện 1: Element hiển thị trên UI có trong DOM/ cây HTML
    // Điều kiện 2: Element không hiển thị trên UI và có trong DOM/HTML
    // Điều kiện 3: Element không hiển thị trên UI và không có trong DOM

    // 4 trang thái chính của Element
    // 1- Visible/Displayed(Hiển thị)
    // => Nếu 1 element hiển thị trên UI và có trong DOM/HTML thì gọi là visible

    // 2- Invisible/Undisplayed(Không hiển thị)
    // Nếu 1 element gọi là Invisible khi thỏa mãn điều kiện 2 và 3

    // 3- Present/Presence(xuất hiện)
    // Nếu 1 element được gọi là Present khi thỏa mãn điều kiện 1 và 2

    // 4- Staleness(Xuất hiện trước đó xong biến mất)
    // Nếu 1 element được gọi là Staleness khi thỏa mãn điều kiện 3

    @Test
    public void TC_01_Visible(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Điều kiện 1: Element hiển thị trên UI có trong DOM/ cây HTML
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_02_Invisible_In_HTML(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("kqvux@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Điều kiện 2: Element không hiển thị trên UI và có trong DOM/HTML
        System.out.println("Start wait:" + getDataTimeNow());
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End wait:" + getDataTimeNow());
    }

    @Test
    public void TC_03_Invisible_Not_In_HTML(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("kqvux@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Điều kiên 3: Element không hiển thị trên UI và không có trong DOM
        System.out.println("Start wait:" + getDataTimeNow());
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End wait:" + getDataTimeNow());
    }

    @Test
    public void TC_04_Staless(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();
        WebElement emailErrormMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.navigate().refresh();
        // Điều kiện 3 - Element không hiển thị trên UI và không có trong DOM
        // Tại thời điểm này mình mong đợi nó không còn xuất hiện nữa
        // Wait until on element is no longer attched to the DOM
        explicitwait.until(ExpectedConditions.stalenessOf(emailErrormMessage));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getDataTimeNow(){
        return new Date().toString();
    }
}
