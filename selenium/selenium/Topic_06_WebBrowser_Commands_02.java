package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Topic_06_WebBrowser_Commands_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void TC_01_Verify_Url() throws MalformedURLException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        System.out.printf(driver.getCurrentUrl());
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        System.out.printf(driver.getCurrentUrl());
    }

    @Test
    public void TC_02_Verify_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        System.out.printf(driver.getTitle());
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
        System.out.printf(driver.getTitle());
    }

    @Test
    public void TC_03_Navigate_Function() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        SleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        System.out.printf(driver.getCurrentUrl());

        driver.navigate().forward();
        SleepInSeconds(2);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        SleepInSeconds(2);
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInSeconds(2);
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void SleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}