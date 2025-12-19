package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Locator {
    // Từ Selenium version 1/2/3/4 đều có 8 loại locator
    // ID
    // Class
    // Name
    // TagName
    // LinkText
    // Partial LinkText
    // Css Selector (Cover hết các trường hợp của 6 loại trên)
    // Xpath (Cover hết các trường hợp của 7 loại trên)
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        //Khởi tạo trình duyệt Chrome
        driver = new ChromeDriver();
        // Mở URL bất kì
        driver.get("https://live.techpanda.org/index.php/customer/account/login");
    }
    @Test
    public void TC_01_id(){
        //Tìm element là Email Address text box
        driver.findElement(By.id("email"));   // Tìm 1 element
        // Tìm nhiều element
        // findElements
        driver.findElement(By.id("search"));

        driver.findElement(By.id("pass"));

        driver.findElement(By.name("send"));

        driver.findElement(By.id("send2"));

        String account = driver.findElement(By.xpath("(//span[text()='Account'])[2]")).getText();
        System.out.println(account);
    }
    @Test
    public void TC_02_Class(){
        driver.findElement(By.className("validate-email"));

        driver.findElement(By.className("validate-password"));
    }
    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("login[username]"));

        driver.findElement(By.name("login[password]"));
    }
    @Test
    public void TC_04_TagName(){
        // Tìm ra có bao nhiêu thẻ HTML giống nhau
        int inputNumber = driver.findElements(By.tagName("input")).size();
        System.out.println(inputNumber);
    }
    @Test
    public void TC_05_LinkText(){
        // Dùng với đường link
        // Tuyệt đối truyền cả text
        driver.findElement(By.linkText("MY ACCOUNT"));

        driver.findElement(By.linkText("ORDERS AND RETURNS"));
    }
    @Test
    public void TC_06_Partical_LinkText(){
        //Dùng với đường link
        // Tương đối: lấy 1 phần hoặc tất cả
        driver.findElement(By.partialLinkText("ACCOUNT"));

        driver.findElement(By.partialLinkText("AND RETURNS"));
    }
    @Test
    public void TC_07_CSS(){
        driver.findElement(By.cssSelector("input[name='login[password]']"));

        driver.findElement(By.cssSelector(".validate-password"));

        driver.findElement(By.cssSelector("#pass"));
    }
    @Test
    public void TC_08_Xpath(){
        driver.findElement(By.xpath("//input[@name='login[password]']"));

        driver.findElement(By.xpath("//input[contains(@class,'validate-password')]"));
    }
    @Test
    public void TC_09_Xpath(){
        driver.findElement(By.xpath("//input[@name='login[password]']"));

        driver.findElement(By.xpath("//input[contains(@class,'validate-password')]"));
    }
    @AfterClass
    public void afterClass(){
    driver.quit();
    }
}
