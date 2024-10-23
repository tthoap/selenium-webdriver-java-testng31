package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Selenium 3,2,1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Selenium 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() throws MalformedURLException {
        // mở ra 1 Url bất kỳ
        driver.get("https://www.facebook.com/");

        //Nếu như có 1 tab thì đóng trình duyệt
        //Nếu có nhiều tab thì đóng cửa sổ đang active
        driver.close();

        //Đóng browser ko care có bao nhiêu cửa sổ đang mở
        driver.quit();

        //2 Hàm chịu ảnh hưởng của các hàm wait
        //trả về 1 element
        driver.findElement(By.xpath(""));
        //Trả về 1 list các Elements
        driver.findElements(By.xpath(""));

        //Dùng lấy ra Url hiện tại đang đứng
        //Home page
        driver.getCurrentUrl();

        //Lấy ra CSS/HTML/JS của page hiện tại dùng verify cách tương đối ̣contains text nào đó
        driver.getPageSource();

        //Lấy ra title của page hiện tại
        driver.getTitle();

        //Lấy ra
        driver.getWindowHandle();

        driver.getWindowHandles();

        //Cookies - Framework
        driver.manage().getCookies();

        //Log - get ra nhung log o Dev tool
        driver.manage().logs().get(LogType.DRIVER);

        //Apply cho viec tìm elelemt
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5));

        //wait cho page dc load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        //set truoc khi dung vs  thu vien cua JavascriptExecutor
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));

        //Selenium 4 Only
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        //
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().getSize();
        driver.manage().window().getPosition();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1366,768));

        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();
        driver.navigate().to("https://tiki.vn/");
        driver.navigate().to(new URL("https://tiki.vn/"));

        //Alert/Window/Tab/Frame Iframe
        driver.switchTo().alert().accept();

        String homePage = driver.getWindowHandle();
        driver.switchTo().window(homePage);

        driver.switchTo().frame(0);

        //Swith ve HTML chua frame truoc do
        driver.switchTo().defaultContent();

        //Tu frame trong di ra frame ngoai chua no
        driver.switchTo().parentFrame();

        //switch ve
    }

    @Test
    public void TC_02_() {
        //
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}