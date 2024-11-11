package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        //driver =  Dai dien cho cai shadow DOM ben ngoai
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        //shadowRootContext = dai dien cho shadow DOM ben trong
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String somtext = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        Assert.assertEquals(shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText(),"some text");
        System.out.println(somtext);

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println("so the input : " + allInput.size());
        shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).click();
        SleepInSeconds(4);
        Assert.assertTrue(shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowContent = nestedShadowHostElement.getShadowRoot();
        String nestedText = nestedShadowContent.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
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