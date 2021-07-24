package Tests.JunitTests;

import org.junit.*;

import static Utils.ConstantsUtils.URL_BASE2;

public class TestClasses extends BaseClassJunit {


    private void doSomething() throws IllegalArgumentException {
        throw new IllegalArgumentException("illegal !!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test01() {
        System.out.println("This is my very first test !");
        driver.navigate().to(URL_BASE2);
//        String s = "Alex";
//        String s2;
//        Assert.assertEquals("String assertion", s, "Alex");
//        int a = 5;
//        int b = 10;
//        Assert.assertEquals("Integer assertion", 15, a + b );
//        Assert.assertFalse(a + b != 15);
//        Assert.assertNotNull(driver);
        String searchQuery = "disney"; // search keyword
        String title =  "Povesti din colectia de aur <mark>Disney</mark>  Nr. 82 - Salvatorii in Australia"; // title extracted from site
        Assert.assertTrue(title.toLowerCase().contains(searchQuery));
        doSomething();
    }

    @Test
    public void test02() {
        System.out.println("test 02");
        // identify the search button place a string and hit run
        driver.navigate().to(URL_BASE2);
    }



}
