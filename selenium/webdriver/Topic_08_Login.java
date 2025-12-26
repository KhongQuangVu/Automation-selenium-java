package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_Login {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_emptyEmail_Pass(){
        driver.get("https://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        WebElement buttonLogin = driver.findElement(By.id("send2"));
        buttonLogin.click();

        String errorEmail = driver.getPageSource();
        String messageEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
        System.out.println(messageEmail);
        Assert.assertTrue(errorEmail.contains("This is a required field."));


        String errorPass = driver.getPageSource();
        String messagePass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
        System.out.println(messagePass);
        Assert.assertTrue(errorPass.contains("This is a required field."));
        System.out.println("--------------------------------------");
    }

    @Test
    public void TC_02_invalid_Email(){
        driver.get("https://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Điền Email
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("12344321@56788765");

        // Điền Password
        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("123456");

        // Click button Login
        WebElement buttonLogin = driver.findElement(By.id("send2"));
        buttonLogin.click();

        String errorEmail1 = driver.getPageSource();
        String messageEmail1 = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
        System.out.println(messageEmail1);
        Assert.assertTrue(errorEmail1.contains("Please enter a valid email address. For example johndoe@domain.com."));
        System.out.println("------------------------------------");
    }

    @Test
    public void TC_03_pass_fail(){
        driver.get("https://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Điền Email
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("kqvu1902@gmail.com");

        // Điền Password
        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("123");

        // Click button Login
        WebElement buttonLogin = driver.findElement(By.id("send2"));
        buttonLogin.click();

        String errorPass1 = driver.getPageSource();
        String messagePass1 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
        System.out.println(messagePass1);
        Assert.assertTrue(errorPass1.contains("Please enter 6 or more characters without leading or trailing spaces."));
        System.out.println("------------------------------------------");
    }

    @Test
    public void TC_04_incorrect_Email_Pass(){
        driver.get("https://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Điền Email
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("kqvu1902@gmail.com");

        // Điền Password
        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("123456");

        // Click button Login
        WebElement buttonLogin = driver.findElement(By.id("send2"));
        buttonLogin.click();
//
//        String errorPass1 = driver.getPageSource();
//        String messagePass1 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
//        System.out.println(messagePass1);
//        Assert.assertTrue(errorPass1.contains("Please enter 6 or more characters without leading or trailing spaces."));
//        System.out.println("------------------------------------------");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
