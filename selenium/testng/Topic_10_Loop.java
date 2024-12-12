package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Loop {
    WebDriver driver;
    Properties props = new Properties();
    String projectpath = System.getProperty("user.dir");
    FileOutputStream outputStream;

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String path = projectpath +"\\dataTest\\user.properties";
        outputStream = new FileOutputStream(path);

    }

    @Test(invocationCount = 2)
    public void Login_01_Register_Success() throws IOException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        //SleepInSeconds(2);

        //register new account with random email
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //SleepInSeconds(2);

        String firstName = "Automation", lastName = "Fc", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        //SleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, "+ fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
       // SleepInSeconds(2);
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        //SleepInSeconds(5);
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        System.out.println("Email address: " + emailAddress);
        System.out.println("Password: " + password);
        props.setProperty("email", emailAddress);
        props.setProperty("Password", password);
        props.store(outputStream,null);
    }

    @AfterClass
    public void afterClass()  {

        driver.quit();
    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(1000 * timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }}
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
    }