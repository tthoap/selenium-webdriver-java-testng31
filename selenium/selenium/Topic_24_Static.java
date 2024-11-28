package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Static {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");

    }

    @Test
    public void TC_01_Dont_Set() {
        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    }

    @Test
    public void TC_02_Less_than() {
        driver.findElement(By.cssSelector("div#start>button")).click();
        SleepInSeconds(4);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Equal() {
        driver.findElement(By.cssSelector("div#start>button")).click();
        SleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    }
    @Test
    public void TC_04_Greater_Than() {
        driver.findElement(By.cssSelector("div#start>button")).click();
        SleepInSeconds(10);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
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