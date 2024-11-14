package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    }

    @Test
    public void TC_02_Tech_Panda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();


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