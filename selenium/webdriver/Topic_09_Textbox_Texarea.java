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
import java.util.Random;

public class Topic_09_Textbox_Texarea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        // Click button Account
        WebElement account = driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']"));
        account.click();

        // Click button My Account
        WebElement myAccount = driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"));
        myAccount.click();

        // Click Create An Account
        WebElement createAnAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        createAnAccount.click();

        driver.findElement(By.id("firstname")).sendKeys("Khong");
        driver.findElement(By.id("middlename")).sendKeys("Quang");
        driver.findElement(By.id("lastname")).sendKeys("Vu");
        // Randow điền email theo thời gian
        long time = System.currentTimeMillis();
        String email = "test" + time + "@gmail.com";

        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Kqvux1902@");
        driver.findElement(By.id("confirmation")).sendKeys("Kqvux1902@");

        // Click Register
        WebElement registerButton = driver.findElement(By.xpath("//button[@title='Register']"));
        registerButton.click();
        Thread.sleep(10000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        // Verify Thank you for registering with Main Website Store.
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Thank you')]")).isDisplayed());

        // Verify First name, Middle name, Last name, Email
        String accountInfo = driver
                .findElement(By.xpath("//div[@class='box-content']/p"))
                .getText();

        // accountInfo:
        // Vu Khong
        // test123@gmail.com

        String[] lines = accountInfo.split("\n");

        String fullNameOnPage = lines[0].trim();
        String emailOnPage    = lines[1].trim();

        // Expected
        String expectedFullName = "Khong Quang Vu";

        // VERIFY
        Assert.assertEquals(fullNameOnPage, expectedFullName);
        Assert.assertEquals(emailOnPage, email);

        // Chuyển sang Mobile
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']")).click();

        // Chọn Samsung Galaxy
        driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).click();

        // Click Add your review
        driver.findElement(By.xpath("//a[contains(text(),'Add Your Review')]")).click();

        driver.findElement(By.id("Quality 1_5")).click();
        driver.findElement(By.id("review_field")).sendKeys("Good");
        driver.findElement(By.id("summary_field")).sendKeys("Best phone");
        driver.findElement(By.id("nickname_field")).clear();
        driver.findElement(By.id("nickname_field")).sendKeys("KQVU");

        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
        Thread.sleep(10000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Your review')]")).isDisplayed());
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Senkeys login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Click mở trang PIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        // Click mở trang Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        String firstName = "Khong";
        String middleName = "Quang";
        String lastName = "Vu";
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);

        String elementID = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).getDomProperty("value");

        driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();

        Random rand = new Random();
        // Tạo số ngẫu nhiên từ 0 đến 9999
        int ranNum = rand.nextInt(1000);

        // Kết hợp chuỗi cố định với số ngẫu nhiên
        String username = "KQVux" + ranNum;
        String pass = "Kqvux1902@";
        driver.findElement(By.xpath("(//input[@autocomplete='off'])[1]")).sendKeys(username);
        driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(pass);
        driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(pass);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String verifyFirstName = driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAttribute("value");
        Assert.assertEquals(verifyFirstName,firstName);

        String verifyMiddleName = driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).getAttribute("value");
        Assert.assertEquals(verifyMiddleName,middleName);

        String verifyLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("value");
        Assert.assertEquals(verifyLastName,lastName);

        String verifyElementID = driver.findElement(By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")).getDomProperty("value");
        Assert.assertEquals(verifyElementID,elementID);

        // Click chọn Immigration
        driver.findElement(By.xpath("//a[normalize-space()='Immigration']")).click();

        // Click chọn Add
        driver.findElement(By.xpath("(//button[@class='oxd-button oxd-button--medium oxd-button--text'])[1]")).click();

        String number = "958378462";
        String comments = "Hello World";
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).sendKeys(number);
        driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comments);

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // click chọn Edit
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value"),number);

        String verifyComments = driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getDomProperty("value");
        Assert.assertEquals(verifyComments,comments);

        // Click chọn logout
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Click chọn My Info
        driver.findElement(By.xpath("//span[normalize-space()='My Info']")).click();
        Thread.sleep(5000);

        String verifyFirstName1 = driver.findElement(By.xpath("//input[@placeholder='First Name']")).getDomProperty("value");
        Assert.assertEquals(verifyFirstName1,firstName);
        System.out.println(verifyFirstName1);

        String verifyMiddleName1 = driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).getDomProperty("value");
        Assert.assertEquals(verifyMiddleName1,middleName);
        System.out.println(verifyMiddleName1);

        String verifyLastName1 = driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getDomProperty("value");
        Assert.assertEquals(verifyLastName1,lastName);
        System.out.println(verifyLastName1);

        String verifyElementID1 = driver.findElement(By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")).getDomProperty("value");
        Assert.assertEquals(verifyElementID1,elementID);
        System.out.println(verifyElementID1);

        // Click chọn Immigration
        driver.findElement(By.xpath("//a[normalize-space()='Immigration']")).click();

        // click chọn Edit
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
        Thread.sleep(5000);

        String verifyNumber1 = driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value");
        Assert.assertEquals(verifyNumber1,number);
        System.out.println(verifyNumber1);

        String verifyComments1 = driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getDomProperty("value");
        Assert.assertEquals(verifyComments1,comments);
        System.out.println(verifyComments1);

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
