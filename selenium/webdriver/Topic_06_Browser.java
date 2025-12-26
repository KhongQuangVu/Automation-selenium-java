package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Browser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Verify_Url(){
        driver.get("http://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Verify URL login
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://live.techpanda.org/index.php/customer/account/login/",loginPageUrl);

        // Click Create An Account
        WebElement createAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
        createAnAccount.click();

        // Verify Url register
        String registerPageUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://live.techpanda.org/index.php/customer/account/create/",registerPageUrl);

    }

    @Test
    public void TC_02_Verify_Title(){
        driver.get("http://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Verify Title
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals("Customer Login",loginPageTitle);

        // Click Create An Account
        WebElement createAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
        createAnAccount.click();

        // Verify Url title
        String registerPageTitle = driver.getTitle();
        Assert.assertEquals("Create New Customer Account",registerPageTitle);

    }

    @Test
    public void TC_03_Navigate_Funtion(){
        driver.get("http://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Click Create An Account
        WebElement createAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
        createAnAccount.click();

        // Verify Url register
        String registerPageUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://live.techpanda.org/index.php/customer/account/create/",registerPageUrl);

        driver.navigate().back();

        // Verify URL login
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://live.techpanda.org/index.php/customer/account/login/",loginPageUrl);

        driver.navigate().forward();

        // Verify Url title
        String registerPageTitle = driver.getTitle();
        Assert.assertEquals("Create New Customer Account",registerPageTitle);
    }

    @Test
    public void TC_04_Get_Page_Source_Code(){
        driver.get("http://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Verify Page Source login
        String pageSourceLogin = driver.getPageSource();
        Assert.assertTrue(pageSourceLogin.contains("Login or Create an Account"));

        // Click Create An Account
        WebElement createAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
        createAnAccount.click();

        // Verify Page Source register
        String pageSourceRegister = driver.getPageSource();
        Assert.assertTrue(pageSourceRegister.contains("Create an Account"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
