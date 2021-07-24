package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.ConstantsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNgClasses extends BaseClass {

    @Test
    public void test01(){
        System.out.println("TEST01");
        driver.get(ConstantsUtils.URL_BASE2);
        double a = 1.05;
        double b = 2.01;
        double sum = a + b; // 3.06, delta in assert is the tolerance 2.91 < sum < 3.11 --> passed
        Assert.assertEquals(sum,3.01,0.1, "Equals !!");
        if (a > b) {
            //
            Assert.fail();
        }
        Assert.assertTrue( a <= b);
    }

    @Test
    public void test02(){
        System.out.println("TEST02");
        driver.get(ConstantsUtils.URL_BASE2);
    }


}
