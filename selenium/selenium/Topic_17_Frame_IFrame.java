package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_iFrame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        SleepInSeconds(3);

        //Iframe//Frame form
        WebElement iframeForm = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        //Swith vao Frame/Iframe truoc khi thao tac voi element ben trong (trang B)
        driver.switchTo().frame(iframeForm);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("Off Campus");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        SleepInSeconds(3);

        //Switch ra ngoai  trang A
        driver.switchTo().defaultContent();

        //CLick login button ben ngoai iframe (trang A)
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();

        //CLick login roi verify error message hien thi
        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

    }

    @Test
    public void TC_02_iFrame() {

        driver.get("https://toidicodedao.com/");

        WebElement frameForm = driver.findElement(By.cssSelector("div.fb-page iframe"));

        Assert.assertTrue(frameForm.isDisplayed());

        driver.switchTo().frame(frameForm);

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(),"405,769 followers");
        System.out.println(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText());
    }
    @Test
    public void TC_03_Frame() {

        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        //INput Customer ID va click Login
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Johnwick");
        driver.findElement(By.cssSelector("a.login-btn")).click();
       // driver.switchTo().defaultContent();
       // SleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());



    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}