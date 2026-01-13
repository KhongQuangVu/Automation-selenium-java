package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // chờ cho việc tìm kiềm element
    }

    @Test
    public void TC_01_Orange_HRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Senkeys login
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Click mở trang PIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        /* Chức năng của Dropdown và hành vi tương ứng
         * 1- Cilck vaod Element để Dropdown xổ ra
         * 2- Chờ cho các item trong Dropdown được load ra hết
         * 3- Kiểm tra tất cả các item xem đâu là item cần chọn
         * 4- Nếu tìm thấy thì click vào item đấy
         * 5- Click vào rồi thì Dropdown tự động close đi
         */

        // Job Title
        selectItemInDropDown("//label[normalize-space()='Job Title']/parent::div/following-sibling::div",
                "//label[normalize-space()='Job Title']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span",
                "IT Manager");
        Assert.assertEquals(driver.findElement(By.xpath("//label[normalize-space()='Job Title']" +
                "/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "IT Manager");
        System.out.println("---------------------------------");

        // Employment Status
        selectItemInDropDown("//label[normalize-space()='Employment Status']/parent::div/following-sibling::div",
                "//label[normalize-space()='Employment Status']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span",
                "Full-Time Contract");
        Assert.assertEquals(driver.findElement(By.xpath("//label[normalize-space()='Employment Status']" +
                "/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Full-Time Contract");
        System.out.println("---------------------------------");

        // Sub Unit
        // 1- Cilck vaod Element để Dropdown xổ ra
        selectItemInDropDown("//label[normalize-space()='Sub Unit']/parent::div/following-sibling::div",
                "//label[normalize-space()='Sub Unit']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-option --indent-')]/span",
                "Administration");
        Assert.assertEquals(driver.findElement(By.xpath("//label[normalize-space()='Sub Unit']" +
                "/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Administration");
        System.out.println("---------------------------------");

        // Include
        selectItemInDropDown("//label[normalize-space()='Include']/parent::div/following-sibling::div",
                "//label[normalize-space()='Include']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span",
                "Past Employees Only");
        Assert.assertEquals(driver.findElement(By.xpath("//label[normalize-space()='Include']" +
                "/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Past Employees Only");

    }
    // Chờ cho đến khi icon load biến mất
    private Boolean isLoadingIconDisappear(){
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElement(By.cssSelector("div.oxd-loading-spinner"))));
    }

    private  void selectItemInDropDown(String parentLocater, String childLocater, String expectedTextItem) throws InterruptedException {
        // 1- Cilck vào Element để Dropdown xổ ra
        driver.findElement(By.xpath(parentLocater)).click();

        // 2- Chờ cho các item trong Dropdown được load ra hết
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath(childLocater)));

        // Lưu tất cả Item vào list chờ thao tác tiếp theo
        // Vòng lặp để duyệt qua tất cả các Element đấy
        Assert.assertNotNull(allItems);
        for (WebElement temp : allItems){
            // Get text của từng elememnt
            String textItem = temp.getText();
            System.out.println(textItem);

            // 3- Kiểm tra tất cả các item xem đâu là item cần chọn
            // Kiểm tra text nào là cái cần chọn thì click vào
            if (textItem.equals(expectedTextItem)){
                // 4- Nếu tìm thấy thì click vào item đấy
                temp.click();
                Thread.sleep(2000);

                // 5- Click vào rồi thì Dropdown tự động close đi
                break;
            }
        }
    }


    @Test
    public void TC_02_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInSelectableDropDown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div","Faster");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(),"Faster");

        selectItemInSelectableDropDown("//span[@id='files-button']","//ul[@id='files-menu']/li/div","ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(),"ui.jQuery.js");

        selectItemInSelectableDropDown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","3");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"3");

        selectItemInSelectableDropDown("//span[@id='salutation-button']","//ul[@id='salutation-menu']/li/div","Mr.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(),"Mr.");
    }

    private  void selectItemInSelectableDropDown(String parentXpath, String childXpath, String expectedTextItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        Assert.assertNotNull(allItems);
        for (WebElement temp : allItems) {
            String textItem = temp.getText();
            System.out.println(textItem);

            if (textItem.equals(expectedTextItem)) {
                temp.click();
                Thread.sleep(2000);

                break;
            }
        }
    }

    @Test
    public void TC_03_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInSelectableDropDown("//div[@role='listbox']","//div[@role='listbox']/div[@class='visible menu transition']/div/span","Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");

        selectItemInSelectableDropDown("//div[@role='listbox']","//div[@role='listbox']/div[@class='visible menu transition']/div/span","Jenny Hess");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");

        selectItemInSelectableDropDown("//div[@role='listbox']","//div[@role='listbox']/div[@class='visible menu transition']/div/span","Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
    }

    private  void selectItemInEditableDropDown(String editableXpath, String childXpath, String expectedTextItem) throws InterruptedException {
        driver.findElement(By.xpath(editableXpath)).clear();
        driver.findElement(By.xpath(editableXpath)).sendKeys(expectedTextItem);
        Thread.sleep(2000);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        Assert.assertNotNull(allItems);
        for (WebElement temp : allItems) {
            String textItem = temp.getText();
            System.out.println(textItem);

            if (textItem.equals(expectedTextItem)) {
                temp.click();
                Thread.sleep(2000);

                break;
            }
        }
    }

    @Test
    public void TC_04_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInSelectableDropDown("//div[@class='btn-group']","//div[@class='btn-group']//ul[@class='dropdown-menu']/li/a","Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Third Option");

        selectItemInSelectableDropDown("//div[@class='btn-group']","//div[@class='btn-group']//ul[@class='dropdown-menu']/li/a","First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
    }

    @Test
    public void TC_05_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropDown("//input[@class='search']","//div[@class='visible menu transition']//span","Bangladesh");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Bangladesh");

        selectItemInEditableDropDown("//input[@class='search']","//div[@class='visible menu transition']//span","Belgium");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Belgium");
    }

    @Test
    public void TC_06_Finpeace() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        selectItemInEditableDropDown("//input[@id='job_id']","//div[@id='job_id_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']","Công nghệ thông tin");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Nghề nghiệp']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(),"Công nghệ thông tin");

        selectItemInEditableDropDown("//input[@id='gender']","//div[@id='gender_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']","Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Giới tính']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(),"Nam");

        selectItemInEditableDropDown("//input[@id='married_status']","//div[@id='married_status_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']","Độc thân, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Tình trạng hôn nhân']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(),"Độc thân, chưa có con");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
