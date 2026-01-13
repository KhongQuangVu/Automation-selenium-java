package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class Topic_12_Button_RadioButton_CheckBox {
    WebDriver driver;
    WebElement element;
    JavascriptExecutor js;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Button() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();

        // Verify button Disabled
        By loginButton = By.xpath("//button[@class='fhs-btn-login']");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        // Verify Backgroup Color button login
        String loginButtonColorRGBA = driver.findElement(loginButton).getCssValue("background-color");
        Color loginButtonColor = Color.fromString(loginButtonColorRGBA);
        String loginButtonColorHexa = loginButtonColor.asHex();

        Assert.assertEquals(loginButtonColorHexa,"#000000");

        // Input dữ liệu
        driver.findElement(By.id("login_username")).sendKeys("0398765421");
        driver.findElement(By.id("login_password")).sendKeys("Kqvux1902@");

        // Verify button login Enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        // Verify color button login Enabled
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");
    }

    @Test
    public void TC_02_Checkbox_Radiobutton(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

        driver.findElement(By.id("eq5")).click();
        Assert.assertTrue(driver.findElement(By.id("eq5")).isSelected());

        driver.findElement(By.id("eq5")).click();
        Assert.assertFalse(driver.findElement(By.id("eq5")).isSelected());
    }

    @Test
    public void TC_03_selectAll_1inAll(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Tìm nhiểu Element
        List<WebElement> allsCheckBox = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        // Click nhiều Element
        for (WebElement checkbox : allsCheckBox){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Verify checkbox Selected
        for (WebElement checkbox : allsCheckBox){
            Assert.assertTrue(checkbox.isSelected(),"Checkbox chưa được chọn" + checkbox.getDomAttribute("value"));
        }
        // Step 04: Bỏ chọn tất cả checkboxes
        for (WebElement checkbox : allsCheckBox) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Select only "Heart Attack"
        WebElement heartAttackCheckbox = driver.findElement(
                By.xpath("//input[@type='checkbox' and @value='Heart Attack']")
        );

        heartAttackCheckbox.click();

        // Step 05: Verify ONLY Heart Attack is selected
        for (WebElement checkbox : allsCheckBox) {
            if (checkbox.equals(heartAttackCheckbox)) {
                Assert.assertTrue(checkbox.isSelected(),
                        "Heart Attack checkbox should be selected");
            } else {
                Assert.assertFalse(checkbox.isSelected(),
                        "Other checkbox should NOT be selected: "
                                + checkbox.getAttribute("value"));
            }
        }
    }

    @Test
    public void TC_04_customCheckbox() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        driver.findElement(By.xpath("//label[@for='id_new_user']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.id("id_new_user")).isSelected());

        WebElement acceptTosLabel = driver.findElement(By.xpath("//label[@for='id_accept_tos']"));
        js.executeScript("arguments[0].scrollIntoView(true);", acceptTosLabel);
        Thread.sleep(2000);
        acceptTosLabel.click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected());
    }

    @Test
    public void TC_05_customRadioButton() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");

        driver.findElement(canthoRadio).click();
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");
        Thread.sleep(2000);

        List<WebElement> checkboxQuang = driver.findElements(By.xpath("//div[@role='checkbox' and starts-with(@aria-label, 'Quảng')]"));
        for (WebElement checkbox : checkboxQuang){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }
        Thread.sleep(2000);
        for (WebElement checkbox : checkboxQuang) {
            // Dùng assertEquals để so sánh String "true" với giá trị thuộc tính
            Assert.assertEquals(checkbox.getDomAttribute("aria-checked"), "true");
        }
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
