package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void TC_01_Fixed_Popup_InDOM() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        SleepInSeconds(2);

        By loginPopup = By.cssSelector("div.custom-dialog-paper");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("Note");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("Note");
        driver.findElement(By.cssSelector("div.custom-dialog-paper button[type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Bạn đã nhập sai tài khoản hoặc mật khẩu!']")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("div.custom-dialog-paper button.close-btn")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());




    }
    @Test
    public void TC_02_Fixed_Popup_InDOM() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        SleepInSeconds(2);

        By loginPopup = By.cssSelector("div.k-popup-account-mb-content");
        By loginPopup2 = By.cssSelector("div#k-popup-account-login-mb div.modal-content");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("tthoa@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123123");

        driver.findElement(By.cssSelector("button#btn-submit-login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03_Fixed_Popup_Not_InDOM() {
        driver.get("https://tiki.vn/");
        SleepInSeconds(5);
        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();

        driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();

        //kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content> div")).isDisplayed());
        SleepInSeconds(2);
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        SleepInSeconds(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");

        //Close popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        SleepInSeconds(2);

        //Khi popup da close thi

        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content> div")).size(), 0);


    }
    @Test
    public void TC_04_Fixed_Popup_Not_InDOM_02_() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("//a[@data-testid='open-registration-form-button']")).click();
        SleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Create a new account']/parent::div/parent::div")).isDisplayed());


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