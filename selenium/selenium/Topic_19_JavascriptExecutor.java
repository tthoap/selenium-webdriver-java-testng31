package selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        System.out.println(jsExecutor);
        System.out.println(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() {
        jsExecutor.executeScript("window.location='https://live.techpanda.org/'");
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain");

        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

    }

    @Test
    public void TC_02_() {

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