package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Relative_Locator {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }
    @Test
    public void TC_01(){
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");

        // Remember Me text
        WebElement remeberMeElememt = driver.findElement(RelativeLocator.with(By.tagName("label"))
                // Đứng trên Login button
                .above(By.xpath("//button[contains(@class,'login-button')]"))
                // Đứng dưới Password textbox
                .below(By.id("Password"))
                // Đứng bên phải Remember Me checkbox
                .toRightOf(By.id("RememberMe"))
                // Đứng bên trái Forgot Password link
                .toLeftOf(By.xpath("//a[text()='Forgot password?']")));
        System.out.println(remeberMeElememt.getText());

        WebElement rememberMeText = driver.findElement(By.xpath("//label[text()='Remember me?']"));
        rememberMeText.getText();
        //System.out.println(rememberMeText);

        String newsletter = driver.findElement(By.xpath("//h2[text()='Newsletter']")).getText();
        System.out.println(newsletter);

        String copyRight = driver.findElement(By.xpath("//span[contains(text(),'Copyright')]")).getText();
        System.out.println(copyRight);

        String powered = driver.findElement(By.xpath("//div[@class='footer-powered-by']")).getText();
        System.out.println(powered);

        String footerMenu01 = driver.findElement(By.xpath("(//h2[starts-with(@class,'footer-menu')])[1]")).getText();
        System.out.println(footerMenu01);

        String footerMenu02 = driver.findElement(By.xpath("(//h2[starts-with(@class,'footer-menu')])[2]")).getText();
        System.out.println(footerMenu02);

        String footerMenu03 = driver.findElement(By.xpath("(//h2[starts-with(@class,'footer-menu')])[3]")).getText();
        System.out.println(footerMenu03);

        String nopCommerce = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).getText();
        System.out.println(nopCommerce);


    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
