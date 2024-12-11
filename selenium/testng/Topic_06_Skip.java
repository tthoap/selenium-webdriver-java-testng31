package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip {

    //CRUD
    //FLow


    @Test
    public void shouldBeRegisterFail() {
        System.out.println("Run TC_01");
    }

   // @Test(priority = 2) or

   @Test(enabled=false)
    public void shouldBeLoginPass() {
        System.out.println("Run TC_02");
    }

    @Test
    public void shouldBeLoginFail() {
        System.out.println("Run TC_03");
    }
}
