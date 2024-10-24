package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        driver.findElement(By.id("")).clear();
        driver.findElement(By.id("")).sendKeys("");
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).findElement(By.id(""));

        //List Generic, phai dung dung kieu du lieu khai bao la Webelement
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        driver.findElement(By.id("")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());

        driver.findElement(By.id("")).getAttribute("id");

        //Tab Accessibility/Properties trong DOM
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("");
        driver.findElement(By.id("")).getDomProperty("");

        //Tab Stlye trong elements
        driver.findElement(By.id("")).getCssValue("background-color");

        //Vi tri cua element so voi do phan giai man hinh
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        //Chieu rong + chieu cao
        Dimension nameSize = driver.findElement(By.id("")).getSize();


        //Location + size
        Rectangle nameTextRect =  driver.findElement(By.id("")).getRect();

        //Location
        Point namePoint = nameTextRect.getPoint();

        //Size
       Dimension addressSize = nameTextRect.getDimension();
       addressSize.getHeight();
       addressSize.getWidth();

       //Shadow Element (JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        //Tu 1 id/class/name/css/xpath co the truy ra nguoc lai tagname HTML
        driver.findElement(By.id("")).getTagName();

        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);

        //Hanh vi tuong tu phim Enter
        driver.findElement(By.id("")).submit();
    }

    @Test
    public void TC_02_() {
        //
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}