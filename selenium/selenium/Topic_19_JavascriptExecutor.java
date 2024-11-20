package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_19_JavascriptExecutor {
    private static final Logger log = LoggerFactory.getLogger(Topic_19_JavascriptExecutor.class);
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    Random random;
    String email;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;
        email = "automation" + new Random().nextInt(99999) + "gmail.com";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_techPanda() {
        jsExecutor.executeScript("window.location='https://live.techpanda.org/'");
        SleepInSeconds(2);
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain");

        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        String homePageURL = (String) jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(homePageURL,"https://live.techpanda.org/");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        SleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button[@title='Add to Cart']")));
        SleepInSeconds(5);

        String samsungText = (String) jsExecutor.executeScript("return document.documentElement.innerText");
        Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        SleepInSeconds(3);

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("input#newsletter")));
        SleepInSeconds(2);

        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + email + "')", driver.findElement(By.cssSelector("input#newsletter")));
        SleepInSeconds(2);

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Subscribe']")));
        SleepInSeconds(2);
        Assert.assertTrue(samsungText.contains("Thank you for your subscription."));

        jsExecutor.executeScript("window.location='https://www.facebook.com/'");

    }

    @Test
    public void TC_02_techPanda() {
        navigateToUrlByJS("https://live.techpanda.org/");

        Assert.assertEquals(getDomain(),"live.techpanda.org");
        Assert.assertEquals(getPageURL(),"https://live.techpanda.org/");

        clickToElementByJS("//a[text()='Mobile']");
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button[@title='Add to Cart']");
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        clickToElementByJS("//a[text()='Customer Service']");
        scrollToElementOnTop("//input@id='newsletter']");
        setAttributeInDOM("//input@id='newsletter']","value", email);

        clickToElementByJS("//button[@title='Subscribe']");
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));


        navigateToUrlByJS("https://www.facebook.com/");

    }
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    @Test
    public void TC_02_Rode() {
        driver.get("https://warranty.rode.com/login");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        // Email empty
        loginBtn.click();
        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage,"Please fill out this field.");

        //Email invalid
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();

        String invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            Assert.assertEquals(invalidEmailMessage,"Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmailMessage,"Please enter an email address.");
        }


        //Email invalid again
         invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();
        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            Assert.assertEquals(invalidEmailMessage,"Please include an '@' in the email address. '" + invalidEmailData + "' is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmailMessage,"Please enter an email address.");
        }

        //Email invalid again

        invalidEmailData = "aaa@aa.";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();
        invalidEmailMessage = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            Assert.assertEquals(invalidEmailMessage,"'.' is used at a wrong position in '" + invalidEmailData.split("@")[1] + "'.");
        } else {
            Assert.assertEquals(invalidEmailMessage,"Please enter an email address.");
        }

        //Email valid
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        loginBtn.click();
    }


    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }
    public String getDomain() {
        return (String) jsExecutor.executeScript("return document.documentElement.domain;");
    }
    public String getPageURL() {
        return (String) jsExecutor.executeScript("return document.documentElement.URL;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(5);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
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