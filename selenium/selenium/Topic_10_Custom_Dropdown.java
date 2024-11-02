package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));


        //wait ngam dinh: ko ro rang cho 1 trang thai cu the nao het
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //1. Click vao 1 the de show ra het cac item ben trong rop-down
        driver.findElement(By.cssSelector("span#number-button")).click();
        SleepInSeconds(2);
        //2.1 no se show all item ra
        //2.2 no se show 1 phan va dang load them hang tram, trieu record
        //xuat hien day du trong HTML
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));

        for (WebElement item:allItems){
            System.out.println("Text item = " + item.getText());
            if(item.getText().equals("10")){
                item.click();
               // break;
            }
        }

        //3.2 neu item can chon nam ben duoi thi 1 so case can scroll  xuong de hien thi roi
        //moi chon (Angular, VueJs, React..)
        //4 truoc khi click can kiem tra neu nhu text cua item bang vs can chon thi click vao

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