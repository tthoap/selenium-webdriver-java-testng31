package testng;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Topic_12_DependOnTest {

    @Test
    public void TC_01_Add_Product() {
        System.out.println("TC_01_Add_Product");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01_Add_Product")
    public void TC_02_Edit_Product() {
        System.out.println("TC_02_Edit_Product");
    }

    @Test(dependsOnMethods = "TC_01_Add_Product")
    public void TC_03_Move_Product() {
        System.out.println("TC_03_Move_Product");
    }

    @Test(dependsOnMethods = "TC_01_Add_Product")
    public void TC_04_Delete_Product() {
        System.out.println("TC_04_Delete_Product");
    }
}
