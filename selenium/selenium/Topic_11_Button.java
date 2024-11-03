package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class Topic_11_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        //Verify if button is disable when not click to checkbox yet
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        SleepInSeconds(2);
        Assert.assertTrue(registerButton.isEnabled());
        String registerBackgroundColorRGB = registerButton.getCssValue("background-color");
        System.out.println("background-color = " + registerBackgroundColorRGB);

        Color registerBackgroundColor = Color.fromString(registerBackgroundColorRGB);
        String registerBackgroundColorHex = registerBackgroundColor.asHex();

        System.out.println("registerBackgroundColor as Hex = " + registerBackgroundColorHex);
        System.out.println("registerBackgroundColor as Hex = " + registerBackgroundColorHex.toLowerCase());
        System.out.println("registerBackgroundColor as Hex = " + registerBackgroundColorHex.toUpperCase());

        Assert.assertEquals(registerBackgroundColorHex.toLowerCase(), "#ef5a00");

    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        //Chuyen qua tab dang nhap
        driver.findElement(By.cssSelector("ul#popup-login-tab_list li.popup-login-tab-login")).click();
        SleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        //Verify dang nhap button is disable
        Assert.assertFalse(loginButton.isEnabled());

        //Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("tthoaptit@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678x@X");
        SleepInSeconds(5);

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(), "#C92127");


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