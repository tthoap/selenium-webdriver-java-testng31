package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Multiple_Browser {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	String username = "selenium_11_01@gmail.com" ;
	String password = "111111";

	@BeforeClass
	@Parameters({"server", "browser"})
	public void beforeClass(String serverName, String browserName) {
		//If-Else
		if(browserName.equalsIgnoreCase("Chrome")){
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("Edge")){
			driver = new EdgeDriver();
		} else if(browserName.equalsIgnoreCase("Safari")){
			driver = new SafariDriver();
		} else {
			throw new RuntimeException("Brower is not valid!!");
		}

		//Switch-Case
		switch (browserName){
			case "Chrome":
				driver = new ChromeDriver();
			case "Edge":
				driver = new EdgeDriver();
			case "Chrome":
				driver = new FirefoxDriver();
			case "Chrome":
				driver = new ChromeDriver();
		}
		
		//Khởi tạo browser
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void loginOnMultitpleBrowser()  {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

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
