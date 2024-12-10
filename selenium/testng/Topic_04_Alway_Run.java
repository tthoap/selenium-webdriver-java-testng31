package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Alway_Run {

    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://www.fahasa.com/");

        //Mở đến trang login
        //Login thành công
        // => không thành công => Fail ở step này
        Assert.assertTrue(false);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void TC_01(){
        System.out.println("Run TC_02");
    }
    @Test
    public void TC_02(){
        System.out.println("Run TC_02");
    }
    @Test
    public void TC_03(){
        System.out.println("Run TC_03");
    }
}
