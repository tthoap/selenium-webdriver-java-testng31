package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {

// tên TC =  hàm/funtion/method của Java
//Theo convention theo từng ngôn ngữ

//Chú thích diễn giải/note
    @Test(description="JIRA#123 -  Register should be failed") 
    //Esclip thì show dòng chú thích này trong log, nhưng Intellij ko show
    public void shouldBeRegisterFail() {
        System.out.println("Run TC_01");
    }

   // @Test(priority = 2) or

   @Test(enabled = false)
    public void shouldBeLoginPass() {
        System.out.println("Run TC_02");
    }

    @Test
    public void shouldBeLoginFail() {
        System.out.println("Run TC_03");
    }
}
