package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Default_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

       // WebElement dualzoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        By dualzoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        By airbagsCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");

        checkToElement(dualzoneCheckbox);
        checkToElement(airbagsCheckbox);
        Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(airbagsCheckbox).isSelected());

        SleepInSeconds(3);
        uncheckToElement(dualzoneCheckbox);
        uncheckToElement(airbagsCheckbox);
        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(airbagsCheckbox).isSelected());

    }
    @Test
    public void TC_02_Telerik_Radio() {

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        checkToElement(twoDieselRadio);
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        SleepInSeconds(2);

        checkToElement(twoPetrolRadio);
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        SleepInSeconds(2);


    }

    @Test
    public void TC_03_Select_All_Checkboxes(){
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for(WebElement checkbox:checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        //chi chon 1
        checkboxes = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for (WebElement checkbox:checkboxes){
            if(checkbox.getAttribute("value").equals(" Heart Attack ") && !checkbox.isSelected()){
                checkbox.click();
                System.out.println("Click to heart attack!");
                SleepInSeconds(2);
            }
        }

        //Verify all checkboxes
        for(WebElement checkbox:checkboxes){
            if (checkbox.getAttribute("value").equals(" Heart Attack ")){
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio(){
        driver.get("https://login.ubuntu.com/");
        By radio = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(radio));
        SleepInSeconds(3);
        Assert.assertTrue(driver.findElement(radio).isSelected());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void checkToElement(By byXpath){
        if(!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            SleepInSeconds(3);
        }

    }
    public void uncheckToElement(By byXpath){
        if(driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            SleepInSeconds(3);
        }

    }

    public void SleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}