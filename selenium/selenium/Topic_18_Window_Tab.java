package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Basic_form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //get ID of current tab
        String basicFormID = driver.getWindowHandle();
        System.out.println("Parent Tab ID" + basicFormID);

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        //CLick GOOGLE
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Google");
        driver.findElement(By.xpath("//textarea[@aria-label='Tìm kiếm']")).sendKeys("Selenium");
        SleepInSeconds(3);

        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("Selenium@hotmail.com");

        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();

        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Lazada - Mua Sắm Hàng Chất Giá Tốt Online");
        System.out.println(driver.getTitle());

        switchToWindowByTitle("Selenium WebDriver");
        SleepInSeconds(3);

        //Close hết các window chỉ để lại parent window
        closeAllWindowWithoutParent(basicFormID);
        //Send email to email textbox
        driver.findElement(By.name("user_email")).sendKeys("Automation@gmail.com");
    }

    public void closeAllWindowWithoutParent(String parentWindow){
        Set<String> allIDs = driver.getWindowHandles();

        //Dung vong lap duyet qua Set ID tren
        for (String id: allIDs){
            if (!id.equals(parentWindow)){
                driver.switchTo().window(id);
                SleepInSeconds(2);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        driver.getTitle();
    }

    @Test
    public void TC_02_Tech_Panda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        SleepInSeconds(2);

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        SleepInSeconds(2);
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(),"COMPARE PRODUCTS");

        //close window
        driver.findElement(By.xpath("//button[@title='Close Window']"));
        SleepInSeconds(2);

        switchToWindowByTitle("Mobile");
        driver.findElement(By.cssSelector("div.actions>a")).click();
        SleepInSeconds(5);
        driver.switchTo().alert().accept();
        SleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");


    }

    @Test
    public void TC_03_Cambridge(){
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.cssSelector("span.cdo-login-button span")).click();
        SleepInSeconds(3);
        switchToWindowByTitle("Login");
        SleepInSeconds(3);
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='username']~span.gigya-error-msg-active")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='password']~span.gigya-error-msg-active")).getText(),"This field is required");
        driver.close();
        SleepInSeconds(3);
        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1~div.pos-header span.headword span")).getText(),"automation");

    }
    @Test
    public void TC_04_DCE_Course_Search(){
        driver.get("https://courses.dce.harvard.edu/");
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        SleepInSeconds(3);

        switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='DCE Login Portal']")).isDisplayed());
        driver.close();
        SleepInSeconds(3);

        switchToWindowByTitle("DCE Course Search");
        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(),"Authentication was not successful. Please try again.");
        driver.switchTo().alert().dismiss();


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowTabByID(String expectedID){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id:allIDs){
            if(!id.equals(expectedID)){
                driver.switchTo().window(expectedID);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle){
        //Get ID of all window/tab
        Set<String> allIDs = driver.getWindowHandles();

        //Dung vong lap duyet qua Set ID tren
        for (String id: allIDs){
            //Cho switch vao tung driver truoc
            driver.switchTo().window(id);
            SleepInSeconds(2);
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}