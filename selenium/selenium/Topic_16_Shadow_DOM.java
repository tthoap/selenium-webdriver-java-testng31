package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        //driver =  Dai dien cho cai shadow DOM ben ngoai
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        //shadowRootContext = dai dien cho shadow DOM ben trong
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String somtext = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        Assert.assertEquals(shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText(), "some text");
        System.out.println(somtext);

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println("so the input : " + allInput.size());
        shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).click();
        SleepInSeconds(4);
        Assert.assertTrue(shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowContent = nestedShadowHostElement.getShadowRoot();
        String nestedText = nestedShadowContent.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
    }

    @Test
    public void TC_02_shadow_DOM_shoppe() {
        driver.get("https://shopee.vn/");
        driver.manage().window().maximize();
        WebElement shawdowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shawdowHostElement.getShadowRoot();

        if (shadowRootContext.findElements(By.cssSelector("home-popup__content")).size() > 0 && shadowRootContext.findElements(By.cssSelector("home-popup__content")).get(0).isDisplayed()) {
            shadowRootContext.findElement(By.cssSelector("div.home-popup__close-area")).click();
            SleepInSeconds(3);
        }
        //Ko hien thi/da closed thi qua step search du lieu
        driver.findElement(By.cssSelector("div.shopee-searchbar-input>input")).sendKeys("IP 15 Promax");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        SleepInSeconds(3);

    }

    @Test
    public void TC_03_shadow_DOM_Book() {
        driver.get("https://books-pwakit.appspot.com/");

        driver.manage().window().maximize();
        WebElement shawdowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext shadowRootContext = shawdowHostElement.getShadowRoot();

        //nested level 1 shadowRoot
        WebElement nestedShadowRoot = shadowRootContext.findElement(By.cssSelector("book-input-decorator"));
        SearchContext nestedLv1ShadowContext = nestedShadowRoot.getShadowRoot();


        //enter "Harry Poster" to search texbox
        shadowRootContext.findElement(By.cssSelector("input#input")).sendKeys("Harry Poster");
        SleepInSeconds(3);
        //CLick search icon
        nestedLv1ShadowContext.findElement(By.cssSelector("div.icon")).click();
        SleepInSeconds(2);

        //Verify san pham dau tien duoc hien thi
        WebElement nestedShadowElementlv1 = shadowRootContext.findElement(By.cssSelector("book-explore._page"));
        SearchContext nestedShadowContentlv1 = nestedShadowElementlv1.getShadowRoot();


        WebElement nestedShadowElementlv2 = nestedShadowContentlv1.findElement(By.cssSelector("ul.books>li:first-child>book-item"));
        SearchContext nestedShadowContentlv2 = nestedShadowElementlv2.getShadowRoot();

        Assert.assertEquals(nestedShadowContentlv2.findElement(By.cssSelector("h2.title")).getText(),"The Psychology of Harry Potter");
        System.out.println(nestedShadowContentlv2.findElement(By.cssSelector("h2.title")).getText());

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