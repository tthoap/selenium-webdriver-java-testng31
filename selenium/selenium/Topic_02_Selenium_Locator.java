package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName"));
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-lower"));
    }

    @Test
    public void TC_02_Name() {
        driver.findElement(By.name("FirstName"));
    }
    @Test
    public void TC_02_Tagname() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_02_LinkText() {
        driver.findElements(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_02_Partial_Linktext() {
        driver.findElement(By.partialLinkText("vendor account"));
    }

    @Test
    public void TC_02_Css() {
        //Css vs ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css vs Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //Css vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        //Css vs TagName
        driver.findElement(By.cssSelector("input"));

        //Css vs Link
        driver.findElement(By.cssSelector("a[href='/blog']"));

        //Css vs Partial Link
        driver.findElement(By.cssSelector("a[href*='vendor']"));

    }

    @Test
    public void TC_02_Xpath() {
        //Xpath vs ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Css vs Class
        driver.findElement(By.xpath("//div[@class='page-title']"));


        //Css vs Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Css vs TagName
        driver.findElement(By.xpath("//input"));

        //Css vs Link
        driver.findElement(By.xpath("//a[@href='/blog']"));
        driver.findElement(By.xpath("//a[text()='Recently viewed products']"));

        //Css vs Partial Link
        driver.findElement(By.xpath("//a[contains(@href,'/blog')]"));
        driver.findElement(By.xpath("//a[contains(text(),'vendor')]"));
    }

    @AfterClass
    public void afterClass() {
       driver.quit();
    }


}