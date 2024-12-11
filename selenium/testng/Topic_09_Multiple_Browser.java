package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Multiple_Browser {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	String username = "selenium_11_01@gmail.com" ;
	String password = "111111";

	String domainURL = null;

	@BeforeClass
	@Parameters( {"server","browser"})
	public void beforeClass( String serverName,@Optional("Firefox") String browserName) {
		//If-Else
		if(serverName.equalsIgnoreCase("Dev")){
			domainURL = "http://dev.techpanda.org";
		} else if(serverName.equalsIgnoreCase("Testing")){
			domainURL = "http://testing.techpanda.org";
		} else if(serverName.equalsIgnoreCase("Staging")){
			domainURL = "http://staging.techpanda.org";
		} else if(serverName.equalsIgnoreCase("Production")){
			domainURL = "http://live.techpanda.org";
		} else {
			throw new RuntimeException("Brower is not valid!!");
		}

		//Switch-Case
		switch (browserName){
			case "Chrome":
				driver = new ChromeDriver();
				break;
			case "Edge":
				driver = new EdgeDriver();
				break;
			case "Firefox":
				driver = new FirefoxDriver();
				break;
//			case "Safari":
//				driver = new SafariDriver();
			default:
				new RuntimeException("Browser is not valid!!");
		}
		
		//Khởi tạo browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void loginOnMultitpleBrowser()  {
		driver.get(domainURL + "/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		
		// ....
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
