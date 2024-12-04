package selenium;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_20_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String Hanoi = "HaNoi.jpg";
    String HCM = "HCM.jpg";
    String HoiAn = "HoiAn.jpg";

    String hanoiPath = projectPath + Hanoi;
    String hcmPath = projectPath + HCM;
    String hoianPath = projectPath + HoiAn;

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Single_File() {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        System.out.println(hanoiPath);
        By inputBy = By.xpath("//input[@type='file']");
        SleepInSeconds(2);
        driver.findElement(inputBy).sendKeys(hanoiPath);
        SleepInSeconds(2);

        driver.findElement(inputBy).sendKeys(hcmPath);
        SleepInSeconds(2);

        driver.findElement(inputBy).sendKeys(hoianPath);
        SleepInSeconds(2);

        //Verify files are loaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+Hanoi+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+HoiAn+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+HCM+"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startbutton:startButtons){
            startbutton.click();
            SleepInSeconds(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+Hanoi+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+HCM+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+HoiAn+"']")).isDisplayed());
    }
    @Test
    public void TC_02_Multiple_files() {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        System.out.println(hanoiPath);
        By inputBy = By.xpath("//input[@type='file']");
        driver.findElement(inputBy).sendKeys(hanoiPath +"\n" + hoianPath +"\n" + hcmPath);
        SleepInSeconds(2);


        //Verify files are loaded successfully
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+Hanoi+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+HoiAn+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+HCM+"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startbutton:startButtons){
            startbutton.click();
            SleepInSeconds(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+Hanoi+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+HCM+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+HoiAn+"']")).isDisplayed());
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