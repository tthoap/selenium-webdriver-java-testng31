package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_14_Actions_02 {
    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        action = new Actions(driver);

        keys = osName.startsWith("Window") ? Keys.CONTROL : Keys.COMMAND;

    }

    @Test
    public void TC_01_Click_And_Hold_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol.ui-selectable li"));

        action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol.ui-selectable li.ui-selected"));

        Assert.assertEquals(allNumberSelected.size(), 4);
    }

    @Test
    public void TC_02_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol.ui-selectable li"));

        //Nhan phim control xuong(chua nha ra)
        action.keyDown(keys.CONTROL).perform();
        SleepInSeconds(2);
        action.click(allNumber.get(0)).click(allNumber.get(5)).click(allNumber.get(10)).click(allNumber.get(15)).pause(Duration.ofSeconds(3)).perform();

        //nha phim control ra
        action.keyUp(keys.CONTROL).perform();
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol.ui-selectable li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);

    }

    @Test
    public void TC_03_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().window().maximize();
        SleepInSeconds(3);
        //action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        action.scrollToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).doubleClick().perform();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());

    }

    @Test
    public void TC_03_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        //CLick chuot phai vao element
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

        By quitContextBy = By.cssSelector("li.context-menu-icon-quit");
        //kiem tra Quit menu hien thi
        Assert.assertTrue(driver.findElement(quitContextBy).isDisplayed());

        //HOver mouse
        action.moveToElement(driver.findElement(quitContextBy)).perform();
        SleepInSeconds(2);

        //Sau khi hover kiem tra no cos trang thai hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        //Click vao Quit
        action.click(driver.findElement(quitContextBy)).perform();
        SleepInSeconds(2);
        driver.switchTo().alert().accept();

        SleepInSeconds(2);
        Assert.assertFalse(driver.findElement(quitContextBy).isDisplayed());

    }
    @Test
    public void TC_04_Drag_And_Drop() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
    }

        @AfterClass
        public void afterClass () {
            driver.quit();
        }

        public void SleepInSeconds ( long timeInSecond){
            try {
                Thread.sleep(timeInSecond * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }