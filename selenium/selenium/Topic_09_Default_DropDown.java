package selenium;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_DropDown {
    WebDriver driver;
    String firstName ="Kevin", lastName = "Laming", emailAddress = getEmailAddress()
            , companyName = "ABC Inc.", password = "123456";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText("31");

        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("October");

        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("2024");

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();

        //Verify fropdown nay la single
        Assert.assertFalse(day.isMultiple());


        //Verify so luongw trong dropdown nay la 32


    }

    @Test
    public void TC_02_Login() {
        //Login

        //Verify

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
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
}