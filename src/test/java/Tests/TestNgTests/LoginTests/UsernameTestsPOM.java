package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import Tests.TestNgTests.LoginTests.Models.LoginModel;
import Tests.TestNgTests.LoginTests.Pages.LoginPage;
import Utils.ConfigReader;
import Utils.GeneralUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UsernameTestsPOM extends BaseClass {

    @DataProvider(name = "UsernameDataProvider")
    public Iterator<Object[]> usernameData() {
        Collection<Object[]> dp = new ArrayList<>();
        dp.add(new String[] { "9", "Username exceeded maximum length" , "1"});
        dp.add(new String[] { "3" ,"", "1" });
        dp.add(new String[] { "2", "Username below minimum number of characters", "1" });
        dp.add(new String[] { "8", "", "1" });
        dp.add(new String[] { "8", "Username does not allow special characters", "4" });
        dp.add(new String[] { "8", "Username does not allow numbers", "3" });
        dp.add(new String[] { "8", "Username does not allow numbers", "6" });
        dp.add(new String[] { "0", "Username is required!", "1" });
        return dp.iterator();
    }

    @Test
    public void basicAuthTest() {
        driver.get(ConfigReader.URL + "/#/login");
        // Page factory line actually initializes all the web elements from the page
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login("dingo", "dingopassword");
    }

    @Test(dataProvider = "UsernameDataProvider")
    public void authUserLengthTest(String length, String userError, String charsetType) {
        driver.get(ConfigReader.URL + "/#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
//        Assert.assertTrue(lp.checkCurrentPage());
        lp.checkCurrentPage2();
        String generatedUsername = GeneralUtils.getRandomStringByLength(Integer.parseInt(length), Integer.parseInt(charsetType));
        String passwordGenerated = GeneralUtils.getRandomStringByLength(8, 5);
        lp.login(generatedUsername, passwordGenerated);
        lp.validateUserError(userError);
    }

    @DataProvider(name = "jsonDp")
    public Iterator<Object[]> jsonDp() {
        Collection<Object []> dp = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            File f = new File("src\\test\\resources\\data\\testdata.json");
            LoginModel lm = mapper.readValue(f, LoginModel.class);
            dp.add(new Object[] { lm } );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dp.iterator();
    }

    @Test(dataProvider = "jsonDp")
    public void jsonTest(LoginModel lm) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(lm.getAccount().getUsername(), lm.getAccount().getPassword());
        lp.validateErrors(lm.getUserError(), lm.getPasswordError(), lm.getGeneralError());
    }

    private LoginModel readModel(File f) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LoginModel.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (LoginModel) unmarshaller.unmarshal(f);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DataProvider(name = "xmlDp")
    public Iterator<Object[]> xmlDp() {
        Collection<Object []> dp = new ArrayList<>();
        File f = new File("src\\test\\resources\\data\\testdata.xml");
        LoginModel lm = readModel(f);
        dp.add(new Object[] {lm} );
        return dp.iterator();
    }

    @Test(dataProvider = "xmlDp")
    public void xmlTest(LoginModel lm) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(lm.getAccount().getUsername(), lm.getAccount().getPassword());
        lp.validateErrors(lm.getUserError(), lm.getPasswordError(), lm.getGeneralError());
    }

    @DataProvider(name = "csvDp")
    public Iterator<Object[]> csvDp () {
        Collection<Object[]> dp = new ArrayList<>();

        try {
            File f = new File("src\\test\\resources\\data\\testdata.csv");
            Reader reader = Files.newBufferedReader(Paths.get(f.getAbsolutePath()));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> csvData = csvReader.readAll();
            dp.addAll(csvData);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return dp.iterator();
    }

    @Test(dataProvider = "csvDp")
    public void csvTest(String username, String password, String userErr, String passErr, String generalErr) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(username, password);
        String ge = " ";
        if (!generalErr.equals(""))
            ge = generalErr;
        lp.validateErrors(userErr, passErr, ge);
    }

}
