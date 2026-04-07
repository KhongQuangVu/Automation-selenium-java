package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic_15_Popup_Iframe {
    WebElement element;
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        // Tắt bảng "Change your password" và các tính năng liên quan đến mật khẩu (bên phải hình bạn gửi)
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Chặn trình quản lý mật khẩu đề xuất lưu/đổi mật khẩu
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Vô hiệu hóa tính năng bảo mật kiểm tra mật khẩu bị rò rỉ (Safe Browsing)
        prefs.put("safebrowsing.enabled", false);

        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Popup() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(1000); // Chờ popup load

        // Step 03: Kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());

        // Step 04 & 05: Nhập thông tin
        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("automationfc");

        // Step 06: Click Đăng nhập & Verify message
        driver.findElement(By.xpath("//button[@type='submit' and text()='Đăng nhập']")).click();
        Thread.sleep(1000);
        String errorMsg = driver.findElement(By.xpath("//div[@class='SnackbarItem-message']")).getText();
        Assert.assertEquals(errorMsg, "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        // Step 07: Đóng popup
        driver.findElement(By.xpath("//button[contains(@class,'close-btn')]")).click();
        Thread.sleep(1000);

        // Step 08: Kiểm tra popup đã đóng
        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);
    }

    @Test
    public void TC_02_Popup(){
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        driver.findElement(By.id("user-login")).clear();
        driver.findElement(By.id("user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("user-password")).clear();
        driver.findElement(By.id("user-password")).sendKeys("123456");

        driver.findElement(By.id("btn-submit-login")).click();

        String errorMessage = driver.findElement(By.xpath("//div[@class='t-text-error' and contains(text(),'Sai tên')]")).getText();
        Assert.assertEquals(errorMessage, "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Popup_notDom(){
        driver.get("https://tiki.vn/");

        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Tài khoản')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sc-2745a82-0 gvDbCz']")).isDisplayed());

        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Email không được để trống')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Mật khẩu không được để trống')]")).isDisplayed());

        driver.findElement(By.xpath("//button[@class='btn-close']")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class,'sc-2745a82-0')]")).size(), 0);
    }

    @Test
    public void TC_04_Random_Popup() throws InterruptedException {
        driver.get("http://www.kmplayer.com/");

        By popup = By.xpath("//div[@role='dialog']");
        if (driver.findElements(popup).size() > 0){
            driver.findElement(By.xpath("//span[@class='notranslate']")).click();
        }
        driver.findElement(By.xpath("//div[@class='close']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void TC_05_Random_Popup() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        By popupTc05 = By.xpath("//div[@class='lepopup-element lepopup-element-2 lepopup-element-rectangle lepopup-animated lepopup-fadeIn']");
        if (driver.findElements(popupTc05).size() >0){
            driver.findElement(By.xpath("//a[@onclick='return lepopup_close();']")).click();
        }
        //Thread.sleep(2000);
        String search = "Agile Testing Explained";
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys(search);
        driver.findElement(By.id("search-submit")).click();

        String keyword = driver.findElement(By.xpath("(//h2[@class='post-title']/a)[1]")).getText();
        System.out.println(keyword);
        Assert.assertEquals(keyword, "Agile Testing Explained");
    }

    @Test
    public void TC_06_Shadow_Dom(){
        driver.get("https://automationfc.github.io/shadow-dom/");

        // ===== Shadow DOM level 1 =====
        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Verify "some text"
        WebElement someText = shadowRoot.findElement(By.cssSelector("span#shadow_content"));
        Assert.assertEquals(
                someText.getText(),
                "some text",
                "Text 'some text' không đúng"
        );

        // Verify checkbox chưa được selected
        WebElement checkbox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(
                checkbox.isSelected(),
                "Checkbox đang được selected"
        );

        // ===== Shadow DOM level 2 (nested) =====
        WebElement nestedShadowHost =
                shadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));

        SearchContext nestedShadowRoot =
                nestedShadowHost.getShadowRoot();

        // Verify "nested text"
        WebElement nestedText =
                nestedShadowRoot.findElement(By.cssSelector("div#nested_shadow_content"));


        Assert.assertEquals(
                nestedText.getText(),
                "nested text",
                "Text 'nested text' không đúng"
        );
    }

    @Test
    public void TC_07_Shadow_Popup() {

        driver.get("https://books-pwakit.appspot.com/");

        WebElement inputDecorator = waitForShadowElement(
                "return document.querySelector('book-app')" +
                        ".shadowRoot.querySelector('app-header')" +
                        ".shadowRoot.querySelector('book-input-decorator')"
        );

        SearchContext inputShadowRoot = inputDecorator.getShadowRoot();

        WebElement searchInput =
                inputShadowRoot.findElement(By.cssSelector("input#input"));
        searchInput.sendKeys("Harry Potter");

        WebElement searchIcon =
                inputShadowRoot.findElement(By.cssSelector("iron-icon[icon='search']"));
        searchIcon.click();

        WebElement bookExplore = waitForShadowElement(
                "return document.querySelector('book-app')" +
                        ".shadowRoot.querySelector('book-explore')"
        );

        SearchContext exploreShadowRoot = bookExplore.getShadowRoot();

        List<WebElement> titles =
                exploreShadowRoot.findElements(
                        By.cssSelector("ul.books li h2.title")
                );

        Assert.assertEquals(titles.size(), 20);
    }

    public WebElement waitForShadowElement(String jsQuery) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return wait.until(driver ->
                (WebElement) js.executeScript(jsQuery)
        );
    }

    @Test
    public void TC_08_Iframe_WordPress(){
        driver.get("https://toidicodedao.com/");

        // Switch qua Iframe/frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        String followersText = driver.findElement(
                By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")
        ).getText();
        System.out.println(followersText);

        // Verify bằng Regex (Chứa số và text)
        Assert.assertTrue(
                followersText.matches("[0-9,]+ followers"),
                "Follower text không đúng format"
        );

        // Quay lại màn hình chứa iframe
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("div#content-sidebar input[class='search-field']")).sendKeys("puppeteer");
        driver.findElement(By.cssSelector("div#content-sidebar input[class='search-field']")).sendKeys(Keys.ENTER);
    }

    @Test
    public void TC_09_Iframe_FormSite(){
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        String tooltip = driver.findElement(By.xpath("//p[@id='tooltip']")).getText();
        System.out.println(tooltip);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='tooltip']")).isDisplayed());

        // Switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@title='Get this form']")).click();

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
