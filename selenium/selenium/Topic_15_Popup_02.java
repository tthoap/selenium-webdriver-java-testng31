package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Random_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");

        SleepInSeconds(10);
        By newsletterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        //if display => Close then go to next step(search)
        if (driver.findElements(newsletterPopup).size() > 0 && driver.findElements(newsletterPopup).get(0).isDisplayed()) {
            System.out.println("Popup is displayed");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) a[onclick='return lepopup_close();']")).click();
            SleepInSeconds(2);
        } else { //If not display => go to next step (search)
            System.out.println("Pop is not displayed");
        }

        //Next step: search data
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        SleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());

    }

    @Test
    public void TC_02_Random_In_DOM() {
        driver.get("https://vnk.edu.vn/");

        //Next step: search data
        findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        SleepInSeconds(3);
        System.out.println(driver.findElement(By.cssSelector("div.title-content")).getText());
        // Assert.assertTrue(findElement(By.cssSelector("div.title-content")).getText().contains("LỊCh Khai GiẢNg ThÁNg 11 – TẶNg BỘ Template M&E GiÚP GiẢM 50% ThỜI Gian VẼ"));

    }

    //Step nao thao tac o man hinh Home moi dung

    public WebElement findElement(By locator) {
        if (driver.findElement(By.cssSelector("div.pum-content.popmake-content")).isDisplayed()) {
            driver.findElement(By.cssSelector("button.pum-close.popmake-close")).click();
            SleepInSeconds(2);
        }
        return driver.findElement(locator);

    }

    @Test
    public void TC_03_Random_Not_In_DOM() {
        driver.get("https://dehieu.vn/");

        By newsletterPopup = By.cssSelector("div.modal-content.css-modal-bt");

        //if display => Close then go to next step(search)
        if (driver.findElements(newsletterPopup).size() > 0 && driver.findElements(newsletterPopup).get(0).isDisplayed()) {
            System.out.println("Popup is displayed");
            int heighBrowser = driver.manage().window().getSize().getHeight();
            if(heighBrowser < 1920){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.close")));
            } else {
                driver.findElement(By.cssSelector("button.close")).click();
            }

        }
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