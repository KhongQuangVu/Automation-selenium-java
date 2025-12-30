package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_Element {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Displayer(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Step 02 & Note - Kiểm tra hiển thị và thao tác

        // --- Kiểm tra Email ---
        WebElement emailTextbox = driver.findElement(By.id("mail"));
        if (emailTextbox.isDisplayed()) {
            System.out.println("Email: Element is displayed");
            emailTextbox.sendKeys("Automation Testing");
        } else {
            System.out.println("Email: Element is not displayed");
        }

        // --- Kiểm tra Age (Under 18) ---
        WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Age (Under 18): Element is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age (Under 18): Element is not displayed");
        }

        // --- Kiểm tra Education ---
        WebElement educationTextArea = driver.findElement(By.id("edu"));
        if (educationTextArea.isDisplayed()) {
            System.out.println("Education: Element is displayed");
            educationTextArea.sendKeys("Automation Testing");
        } else {
            System.out.println("Education: Element is not displayed");
        }

        // Step 03 - Kiểm tra phần tử "Name: User5" không hiển thị
        // Lưu ý: User5 thường nằm trong phần Hover, chỉ hiển thị khi di chuột vào
        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (user5Text.isDisplayed()) {
            System.out.println("Name: User5: Element is displayed");
        } else {
            System.out.println("Name: User5: Element is not displayed");
        }
    }



    @Test
    public void TC_02_isEnabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox1 = driver.findElement(By.id("mail"));
        if (emailTextbox1.isEnabled()) {
            System.out.println("Email: Element is enabled");
        } else {
            System.out.println("Email: Element is not disabled");
        }

        WebElement ageUnder18Radio1 = driver.findElement(By.id("under_18"));
        if (ageUnder18Radio1.isEnabled()) {
            System.out.println("Age (Under 18): Element is disabled");
        } else {
            System.out.println("Age (Under 18): Element is not disabled");
        }

        WebElement educationTextArea1 = driver.findElement(By.id("edu"));
        if (educationTextArea1.isEnabled()) {
            System.out.println("Education: Element is enabled");
        } else {
            System.out.println("Education: Element is not disabled");
        }

        WebElement job1 = driver.findElement(By.id("job1"));
        if (job1.isEnabled()) {
            System.out.println("Job Role 1: Element is enabled");
        } else {
            System.out.println("Job Role 1: Element is not disabled");
        }

        WebElement job2 = driver.findElement(By.id("job2"));
        if (job2.isEnabled()) {
            System.out.println("Job Role 2: Element is enabled");
        } else {
            System.out.println("Job Role 2: Element is not disabled");
        }

        WebElement development = driver.findElement(By.id("development"));
        if (development.isEnabled()) {
            System.out.println("Check box Development: Element is enabled");
        } else {
            System.out.println("Check box Development: Element is not disabled");
        }

        WebElement slider_1 = driver.findElement(By.id("slider-1"));
        if (slider_1.isEnabled()) {
            System.out.println("Slider 1: Element is enabled");
        } else {
            System.out.println("Slider 1: Element is not disabled");
        }

        System.out.println("-----------------------------------------");

        WebElement disabledPassword = driver.findElement(By.id("disable_password"));
        if (disabledPassword.isEnabled()) {
            System.out.println("Password: Element is enabled");
        } else {
            System.out.println("Password: Element is not disabled");
        }

        WebElement disabledRadio = driver.findElement(By.id("radio-disabled"));
        if (disabledRadio.isEnabled()) {
            System.out.println("Radio -disabled: Element is enabled");
        } else {
            System.out.println("Radio -disabled: Element is not disabled");
        }

        WebElement biography = driver.findElement(By.id("bio"));
        if (biography.isEnabled()) {
            System.out.println("Biography: Element is enabled");
        } else {
            System.out.println("Biography: Element is not disabled");
        }

        WebElement job3 = driver.findElement(By.id("job3"));
        if (job3.isEnabled()) {
            System.out.println("Jod Role 3: Element is enabled");
        } else {
            System.out.println("Jod Role 3: Element is not disabled");
        }

        WebElement checkboxDisabled = driver.findElement(By.id("check-disbaled"));
        if (checkboxDisabled.isEnabled()) {
            System.out.println("Interests: Element is enabled");
        } else {
            System.out.println("Interestes: Element is not disabled");
        }

        WebElement slider2 = driver.findElement(By.id("slider-2"));
        if (slider2.isEnabled()) {
            System.out.println("Slider 2: Element is enabled");
        } else {
            System.out.println("Slider 2: Element is not disabled");
        }
        System.out.println("-----------------------------------");
    }

    @Test
    public void TC_03_isSelected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Click Radio
        WebElement radioUnder18 = driver.findElement(By.id("under_18"));
        radioUnder18.click();

        // Click Checkbox
        WebElement checkboxJava = driver.findElement(By.id("java"));
        checkboxJava.click();

        // Kiểm tra phần tử Selected Radio Under 18
        if (radioUnder18.isSelected()){
            System.out.println("Radio Under 18: Element is selected");
        } else {
            System.out.println("Radio Under 18: Element is de-selected");
        }

        // Kiểm tra phần tử Selected checkbox Java
        if (checkboxJava.isSelected()){
            System.out.println("Checkbox Java: Element is selected");
        } else {
            System.out.println("Checkbox Java: Element is de-selected");
        }

        // Click bỏ chọn Checkbox
        WebElement checkboxJava1 = driver.findElement(By.id("java"));
        checkboxJava1.click();

        // Kiểm tra phần tử Selected checkbox Java
        if (checkboxJava1.isSelected()){
            System.out.println("Checkbox Java: Element is selected");
        } else {
            System.out.println("Checkbox Java: Element is de-selected");
        }
    }

    @Test
    public void TC_04_Mailchimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        // Điền email
        driver.findElement(By.id("email")).sendKeys("kqvu1902@gmail.com");
        // Điền password
        driver.findElement(By.id("new_password")).sendKeys("test");
        Thread.sleep(2000);
        // Lowercase character
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("TEST");
        Thread.sleep(2000);
        // Uppercase char
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("1234");
        Thread.sleep(2000);
        // Number char
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("@#$%!");
        Thread.sleep(2000);
        // Special char
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("kqvu1902@gmail.com");
        Thread.sleep(2000);
        // 8 char
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("Kqvux1902@");
        Thread.sleep(2000);
        // Full
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
