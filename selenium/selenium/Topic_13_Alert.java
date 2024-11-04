package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;

public class Topic_13_Alert {
    WebDriver driver;

    WebDriverWait explicitWait;

    String projectLocation = System.getProperty("user.dir");
    By result = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        SleepInSeconds(3);

        //Wait cho alert
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        SleepInSeconds(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        SleepInSeconds(3);

        Assert.assertEquals(driver.findElement(result).getText(),"You clicked: Cancel");

    }
    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        SleepInSeconds(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "Selenium WebDriver";
        alert.sendKeys(text);
        SleepInSeconds(2);
        alert.accept();

        Assert.assertEquals(driver.findElement(result).getText(),"You entered: " + text);
    }
    @Test
    public void TC_04_Authen_Alert_AutoIT() throws IOException {
        //Thu vien Alert ko support cho Authentication alert duoc
        //Chrome DevTool Prorotol (CDP)

        //Cach 1: truyen thang user/pass vao URl
        //driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        //Cach 2: Chay tren Windows (Auto IT)
        //Thuc thi doan code AudoIT de cho Alert xuat hien
        Runtime.getRuntime().exec(new String[] {projectLocation + "\\AutoIT\\authen_firefox.exe", "admin", "admin"});

        driver.get("http://the-internet.herokuapp.com/basic_auth");
        SleepInSeconds(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());


    }
    @Test
    public void TC_05_Authen_Pass_To_URL()  {
        String userName = "admin";
        String password = "admin";

        driver.get("http://" + userName +":" + password +"@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_06_Authen_Pass_To_URL()  {
        String userName = "admin";
        String password = "admin";

        driver.get("http://" + userName +":" + password +"@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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