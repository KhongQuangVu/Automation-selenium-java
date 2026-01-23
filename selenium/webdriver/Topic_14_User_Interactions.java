package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.List;

public class Topic_14_User_Interactions {
    WebDriver driver;
    WebElement element;
    JavascriptExecutor js;
    Actions actions;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        actions.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']")).getText(),
                "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        driver.findElement(By.xpath("//div[@class='brz-popup2__close']//*[name()='svg']")).click();

        actions.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'fhs_center_right')]"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertTrue(driver.findElement(By.xpath
                ("//div[@class='fhs_menu_title fhs_center_left']//span[@class='menu-title'][contains(text(),'Sách Trong Nước')]")).isDisplayed());
    }

    @Test
    public void TC_03_ClickandHover(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems = driver.findElements(By.xpath("//ol[@class='ui-selectable']//li"));
        Assert.assertEquals(allItems.size(),30);

        actions.clickAndHold(allItems.getFirst()).moveToElement(allItems.get(11)).release().perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertEquals(driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[contains(@class,'ui-selected')]")).size(),12);
    }

    @Test
    public void TC_04_Click_Select() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems = driver.findElements(By.xpath("//ol[@class='ui-selectable']//li"));
        Assert.assertEquals(allItems.size(),30);

        actions.keyDown(Keys.CONTROL).perform();
        allItems.get(0).click();
        allItems.get(4).click();
        allItems.get(7).click();
        allItems.get(9).click();
        allItems.get(14).click();
        allItems.get(19).click();
        allItems.get(24).click();
        actions.keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
    }

    @Test
    public void TC_05_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
        actions.contextClick(rightClick).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Quit']")).click();
        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void TC_06_Drop_Drag() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

        actions.clickAndHold(smallCircle).moveToElement(bigCircle).release(bigCircle).perform();
        Thread.sleep(3000);
    }

    @AfterClass
    public void afterClass(){

        driver.quit();
    }
}
