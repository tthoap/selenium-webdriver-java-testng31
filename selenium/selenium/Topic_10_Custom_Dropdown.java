package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

    //@Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Slower");
        SleepInSeconds(3);
        selectItemInDropdown("span#files-button", "ul#files-menu div", "jQuery.js");
        selectItemInDropdown("span#number-button", "ul#number-menu div", "18");
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"18");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Mrs.");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("i.dropdown.icon", "div.item span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");
        SleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item span", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");
        SleepInSeconds(3);

        selectItemInDropdown("i.dropdown.icon", "div.item span", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        SleepInSeconds(4);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("div.btn-group li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
        SleepInSeconds(3);
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search", "div.item span", "Andorra");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.item span")).getText(),"Andorra");
        SleepInSeconds(3);
    }

    @Test
    public void TC_05_Nopcommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
        Assert.assertEquals(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).getText(),"18");

        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "May");
        Assert.assertEquals(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='5']")).getText(),"May");

        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1917");
        Assert.assertEquals(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1917']")).getText(),"1917");
        SleepInSeconds(3);
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

    public void selectItemInDropdown(String parentCss, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).click();

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item:allItems){
            System.out.println("Text item = " + item.getText());
            if(item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }
    public void selectItemInEditableDropdown(String parentCss, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item:allItems){
            System.out.println("Text item = " + item.getText());
            if(item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }


}