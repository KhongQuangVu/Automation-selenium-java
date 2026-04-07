package webdriver;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_16_Window {
    WebDriver driver;
    WebElement element;
    Select select;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void switchToNewWindow(String parentWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for(String window : allWindows){
            if (!window.equals(parentWindow)){
                driver.switchTo().window(window);
                break;
            }
        }
    }

    @Test
    public void TC_01_Window_Tab() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentWindow = driver.getWindowHandle();

        // Switch to Google
        driver.findElement(By.linkText("GOOGLE")).click();
        switchToNewWindow(parentWindow);
        Thread.sleep(1500);
        Assert.assertEquals(driver.getTitle(), "Google");
        driver.close();
        driver.switchTo().window(parentWindow);
        Thread.sleep(1500);

        // Facebook
        driver.findElement(By.linkText("FACEBOOK")).click();
        switchToNewWindow(parentWindow);
        Thread.sleep(1500);
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
        driver.close();
        driver.switchTo().window(parentWindow);
        Thread.sleep(1500);

        // TIKI
        driver.findElement(By.linkText("TIKI")).click();
        switchToNewWindow(parentWindow);
        Thread.sleep(1500);
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        driver.close();
        driver.switchTo().window(parentWindow);
        Thread.sleep(1500);

        // Lazada
        driver.findElement(By.linkText("LAZADA")).click();
        switchToNewWindow(parentWindow);
        Thread.sleep(1500);
        Assert.assertEquals(driver.getTitle(),"Lazada Việt Nam | Mua Sắm Online, Giá Tốt Nhất Mỗi Ngày");
        driver.close();
        driver.switchTo().window(parentWindow);

        // Trang DEMO
        String title = driver.getTitle();
        Assert.assertEquals(title, "Selenium WebDriver");
        System.out.print(title);
    }

    @Test
    public void TC_02_Window_Tab() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='IPhone']/ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        // Switch
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        switchToNewWindow(parentWindow);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();
        driver.switchTo().window(parentWindow);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),
                "The comparison list was cleared.");

    }

    @Test
    public void TC_03_Window_Tab(){
        driver.get("https://dictionary.cambridge.org/vi/");

        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//div[@class='hdn hdib-s']//span[text()='Đăng nhập']")).click();
        switchToNewWindow(parentWindow);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        Assert.assertEquals(driver.findElement(By.xpath(
                "//span[@id='gigya-error-msg-gigya-login-form-loginID']")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.xpath(
                "//span[@id='gigya-error-msg-gigya-login-form-password']")).getText(),"This field is required");
        driver.close();
        driver.switchTo().window(parentWindow);

        String keyword = "automation";
        driver.findElement(By.xpath("//input[@id='searchword']")).sendKeys(keyword);
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                "(//div[@class='pos-header dpos-h']//span[@class='hw dhw'])[1]")).getText(),keyword);
    }

    @Test
    public void TC_04_Window_Tab() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");

        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@data-action='login']")).click();
        switchToNewWindow(parentWindow);
        Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='c02ea9741 cf3efaada']")).isDisplayed());
        driver.close();
        driver.switchTo().window(parentWindow);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sam-wait']")).isDisplayed());
        driver.findElement(By.xpath("//button[@class='fa fa-times sam-wait__close']")).click();

        driver.findElement(By.xpath("//input[@id='crit-keyword']")).sendKeys("Hello");

        select = new Select(driver.findElement(By.xpath("//select[@id='crit-srcdb']")));
        select.selectByVisibleText("Extension January & Spring Term 2026");
        Thread.sleep(2000);

        select = new Select(driver.findElement(By.xpath("//select[@id='crit-session']")));
        select.selectByVisibleText("Full Term");
        Thread.sleep(2000);

        driver.findElement(By.id("search-button")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='panel__content']//h2[text()='Search Results']")).isDisplayed());
        String search = driver.findElement(By.xpath("//h2[text()='Search Results']")).getText();
        System.out.print(search);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
