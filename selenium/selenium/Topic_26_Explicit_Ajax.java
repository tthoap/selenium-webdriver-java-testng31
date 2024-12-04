package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_26_Explicit_Ajax {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String Hanoi = "HaNoi.jpg";
    String HCM = "HCM.jpg";
    String HoiAn = "HoiAn.jpg";

    String hanoiPath = projectPath + Hanoi;
    String hcmPath = projectPath + HCM;
    String hoianPath = projectPath + HoiAn;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Calender() {

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Wait and verify Calender element is displayed
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        //wait and verify text hien thi
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display.")));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a[text()='3']"))).click();

        //Wait cho ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='ContentPlaceholder1_Panel1']>div.raDiv"))));

        //wait and verify text cua ngay da chon hien thi
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"Tuesday, December 3, 2024")));

    }
    @Test
    public void TC_02_Go_File() {
        driver.get("https://gofile.io/?t=uploadFiles");

        //wait cho upload icon hien thi vaf sendkey upload 2 hinh
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#home_uploadFile"))).sendKeys(hanoiPath +"\n" + hoianPath +"\n" + hcmPath);

        

    }
    @Test


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}