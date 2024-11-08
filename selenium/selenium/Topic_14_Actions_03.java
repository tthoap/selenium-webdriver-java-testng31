package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;


public class Topic_14_Actions_03 {
    WebDriver driver;
    Actions action;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Drag_And_Drop_HTML4() {

        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(smallCircle,bigCircle).perform();
        Assert.assertEquals(bigCircle.getText(),"You did great!");

        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
    }
    @Test
    public void TC_02_Drag_And_Drop_HTML5_Selenium_Not_Support() {

        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement sourceSquare = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetSquare = driver.findElement(By.cssSelector("div#column-b"));

        action.dragAndDrop(sourceSquare,targetSquare).perform();
        SleepInSeconds(3);
        /*Assert.assertEquals(bigCircle.getText(),"You did great!");

        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");*/
    }
    @Test
    public void TC_02_Drag_And_Drop_HTML5() throws IOException {

        driver.get("https://automationfc.github.io/drag-drop-html5/");


        String jqueryDragDropContent = getContentFile(projectPath + "\\dragDrop\\dragAndDrop.js");
        System.out.println("duong dan la: " + jqueryDragDropContent);

        //Drag from A to B
        jsExecutor.executeScript(jqueryDragDropContent);
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "A");

        //Drag from B to A
        jsExecutor.executeScript(jqueryDragDropContent);
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "B");


    }
    @Test
    public void TC_02_Drag_And_Drop_HTML5_Robot() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        //Drag from A to B
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        SleepInSeconds(2);
        /*Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "A");

        //Drag from B to A
        dragAndDropHTML5ByXpath("div#column-b","div#column-a");
        SleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "B");*/
    }
    @Test
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {
        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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