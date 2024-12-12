package testng;

import org.testng.annotations.*;

public class Topic_01_Annotation {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Run before suite");

    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("Run before Class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Run before Method");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Run before Test");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("Run After suite");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("Run After Class");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("Run After Method");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("Run After Test");
    }

    @Test
    public void TC_01(){
        System.out.println("Run TC_02");
    }
    @Test
    public void TC_02(){
        System.out.println("Run TC_02");
    }
    @Test
    public void TC_03(){
        System.out.println("Run TC_03");
    }
}
