package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_27_Mix_wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Element_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Wait với implicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với implicit
        System.out.println("Start = " + getDateTimeNow());
        try {
            driver.findElement(By.cssSelector("input#email124"));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        By emailTextboxBy = By.cssSelector("input#email6565");
        //Wait với explicit =3, implicit = 0
        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextboxBy));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_Element() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với explicit =3, implicit = 0
        WebElement emailTextbox = null;

        System.out.println("Start = " + getDateTimeNow());
        try {
            emailTextbox = driver.findElement(By.cssSelector("input#email6565"));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }


        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(emailTextbox));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_04_Element_Not_Found_Mix_Equal() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với explicit
        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email6565")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }

    }
    @Test
    public void TC_04_Element_Not_Found_Mix_Explixit_Longer() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với explicit
        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email6565")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }

    }
    @Test
    public void TC_04_Element_Not_Found_Mix_Implixit_Longer() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(1));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với explicit
        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email6565")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }


    }

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
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