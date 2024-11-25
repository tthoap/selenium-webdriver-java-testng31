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

public class Topic_21_Wait_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
// link exercise : https://docs.google.com/document/d/1YHmFR2m0aPi29TQJm67_DAUXGUajm5qF7qpCdyACyxg/edit?tab=t.0#heading=h.1koycpqriocf
    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com");
        //Điều kiện 1- Element có trên UI và có trong HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }
    @Test
    public void TC_02_Invisible() {
       //Điều kiện 2 - Element ko hiển thị trên UI và có trong DOM/HTML
        //Điều kiện 3 - Element ko hiển thị trên UI và ko có trong DOM/HTML

        driver.get("https://www.facebook.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Click Create new account
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        //wait cho Sign up button hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("websubmit")));
        //Wait cho Age textbox hien thi
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input#birthday_age_inner")));

        //Click Signup button 2 lan thi Age textbox hien thi
        driver.findElement(By.name("websubmit")).click();
        driver.findElement(By.name("websubmit")).click();

        //Wait cho Age textbox hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#birthday_age_inner")));

        //send value to Age textbox
        driver.findElement(By.cssSelector("input#birthday_age_inner")).sendKeys("30");
        SleepInSeconds(2);

        //Mo lai page home
        driver.get("https://www.facebook.com");
        SleepInSeconds(2);

        //Wait cho Age textbox se ko hien thi va ko co trong HTML/DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input#birthday_age_inner")));


    }

    @Test
    public void TC_03_Presence() {
        //Mo lai page home
        driver.get("https://www.facebook.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Click Create new account
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        //wait cho Sign up button hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("websubmit")));

        //Click Custom thi drop-down hien thi
        driver.findElement(By.xpath("//label[text()='Custom']/input")).click();

        //Điều kiện 1 - Element có trên UI và có trong HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("select#preferred_pronoun")));

        //Select Male de drop-down ko hien thi tren UI
        driver.findElement(By.xpath("//label[text()='Male']/input")).click();
        SleepInSeconds(2);

        //Điều kiện 2 - Element ko hiển thị trên UI và có trong DOM/HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("select#preferred_pronoun")));



    }
    @Test
    public void TC_04_Staleness() {
        //Mo lai page home
        driver.get("https://www.facebook.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Click Create new account
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        //wait cho Sign up button hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("websubmit")));

        //Click Custom thi drop-down hien thi
        driver.findElement(By.xpath("//label[text()='Custom']/input")).click();

        //Điều kiện 1 - Element có trên UI và có trong HTML
        WebElement dropdown  = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select#preferred_pronoun")));

        //CLick 'Already have an account?' để mở page khác và ko có dropdown trong HTML/DOM
        driver.findElement(By.xpath("//a[@aria-label='Already have an account?']")).click();
        SleepInSeconds(2);

        //Điều kiện 2 - Element ko hiển thị trên UI và ko có trong DOM/HTML
        explicitWait.until(ExpectedConditions.stalenessOf(dropdown));


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