package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_22_Wait_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //Total Time = 13s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void TC_01_FindElement() {
        //TH1 - nếu tìm thấy duy nhất 1 element
        //return đúng element đó, ko cần wait hết total time
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.cssSelector("input#FirstName"));

        //TH2 - nếu tìm nhiều hơn 1 element
        //driver.findElement(By.cssSelector("input[type='text']")).sendKeys("test");

        //TH3 - nếu ko tìm thấy element nào
        //Mới đầu vào tìm ko thấy thì tìm lại, tìm thấy thì ko cần chờ
        // tìm lại mỗi polling time (0.5s) và ko thấy nữa thì chờ hết total time rồi trả về NosuchElementEXception
        driver.findElement(By.xpath("//a[text()='Fahrenheit 451 by Ray Bradbury']"));


    }

    @Test
    public void TC_02_FindElement() {
        //TH1 - nếu tìm thấy duy nhất 1 element
        List<WebElement> elements = null;

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        //TH2 - nếu tìm nhiều hơn 1 element
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());



        //TH3 - nếu ko tìm thấy element nào
        //Mới đầu vào tìm ko thấy thì tìm lại, tìm thấy thì ko cần chờ
        // tìm lại mỗi polling time (0.5s) và ko thấy nữa thì chờ hết total time rồi :
        //1 - trả và list element = 0
        //2 - ko đánh fail TC mà run step tiếp theo
        elements = driver.findElements(By.xpath("//a[text()='Fahrenheit 451 by Ray Bradbury']"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(),0);
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