package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Relative_locator() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        //Login button
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        //Remember Me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        //Forgot Password element
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        WebElement rememberMeTextElement = driver.findElement(RelativeLocator.with(By.tagName("label")).above(loginButtonElement).toRightOf(rememberMeCheckboxBy).toLeftOf(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());
    }
    @Test
    public void TC_01_Xpath() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        driver.findElement(By.id("Email"));
        driver.findElement(By.className("password"));
        driver.findElement(By.name("Password"));
        driver.findElement(By.cssSelector("button.register-button"));

        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[@title='Italian']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'/reg/')]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}