package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.ConstantsUtils;
import org.testng.annotations.Test;

public class SearchTests extends BaseClass {

    @Test
    public void searchTest() {
        System.out.println("Here we do the search test");
        driver.get(ConstantsUtils.URL_BASE2 + "/catalogsearch/result/?q=masina");
    }
}
