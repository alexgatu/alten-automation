package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import Tests.TestNgTests.LoginTests.Models.AccountModel;
import Tests.TestNgTests.LoginTests.Models.LoginModel;
import Tests.TestNgTests.LoginTests.Pages.LoginPage;
import Utils.ConfigReader;
import Utils.ExcelReader;
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
import java.sql.*;
import java.util.*;

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

    @DataProvider(name = "xlsxDp")
    public Iterator<Object[]> xlsxDp() {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            File f = new File("src\\test\\resources\\data\\testdata.xlsx");
            String[][] excelData = ExcelReader.readExcelFile(f, "Sheet1", true, true);
            Collections.addAll(dp, excelData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dp.iterator();
    }

    @Test(dataProvider = "xlsxDp")
    public void xlsxTest(String username, String password, String userErr, String passErr, String generalErr) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(username, password);
        lp.validateErrors(userErr, passErr, generalErr);
    }

    // Data provider with LoginModel for excel
    @DataProvider(name = "xlsxDp2")
    public Iterator<Object[]> xlsxDp2() {
        Collection<Object[]> dp = new ArrayList<>();
        try {
            File f = new File("src\\test\\resources\\data\\testdata.xlsx");
            String[][] excelData = ExcelReader.readExcelFile(f, "Sheet1", true, true);
            for (int i = 0; i< excelData.length; i++) {
                String username = excelData[i][0];
                String password = excelData[i][1];
                String userErr = excelData[i][2];
                String passErr = excelData[i][3];
                String generalErr = excelData[i][4];
                AccountModel am = new AccountModel(username, password);
                LoginModel lm = new LoginModel(am, userErr, passErr, generalErr);
                dp.add( new Object[] {lm});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dp.iterator();
    }

    @Test(dataProvider = "xlsxDp2")
    public void xlsxModelTest(LoginModel lm) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(lm.getAccount().getUsername(), lm.getAccount().getPassword());
        lp.validateErrors(lm.getUserError(), lm.getPasswordError(), lm.getGeneralError());

    }

    @DataProvider(name = "sqlDp")
    public Iterator<Object[]> sqlDp() {
        Collection<Object[]> dp = new ArrayList<>();
        String query = "SELECT * FROM alten_auto.login;";
        try {
            Connection con= DriverManager.getConnection(
                    ConfigReader.DB_JDBC,ConfigReader.DB_USER,ConfigReader.DB_PASS);
            Statement statement = con.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String username = GeneralUtils.sanitizeNullString(results.getString("username"));
                String password = GeneralUtils.sanitizeNullString(results.getString("password"));
                String userErr = GeneralUtils.sanitizeNullString(results.getString("usererr"));
                String passErr = GeneralUtils.sanitizeNullString(results.getString("passerr"));
                String generalErr = GeneralUtils.sanitizeNullString(results.getString("generalerr"));
                AccountModel am = new AccountModel(username, password);
                LoginModel lm = new LoginModel(am, userErr, passErr, generalErr);
                dp.add( new Object[] {lm});
            }
            statement.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dp.iterator();
    }

    @Test(dataProvider = "sqlDp")
    public void sqlDataTest(LoginModel lm) {
        driver.get(ConfigReader.URL + "#/login");
        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        lp.login(lm.getAccount().getUsername(), lm.getAccount().getPassword());
        lp.validateErrors(lm.getUserError(), lm.getPasswordError(), lm.getGeneralError());
    }

}
