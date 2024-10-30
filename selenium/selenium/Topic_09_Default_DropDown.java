package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_09_Default_DropDown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("");

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText("31");

        driver.findElement(By.cssSelector("input#Email")).sendKeys("");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("");


    }

    @Test
    public void TC_02_Login() {

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