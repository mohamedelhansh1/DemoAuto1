package TestCases;

import org.apache.logging.log4j.util.Constants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class TestLogin {
    WebDriver driver;
    HomePage home;
    LoginPage login;
    Dashboard dashboard;
    RegistrationPage registration ;
    Sheet sheet;
    DataSheet Dsheet ;
    static ExcelUtils excelUtils = new ExcelUtils();
    static String excelFilePath = Constants.Path_TestData+Constants.File_TestData;

    @BeforeTest

    void setup()
   {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        this.home = new HomePage(driver);
        this.login = new LoginPage(driver);
        this.dashboard = new Dashboard(driver);
        this.registration= new RegistrationPage(driver);
        this.sheet=new Sheet(driver);
        this.Dsheet=new DataSheet(driver);
    }

   @Test (priority = 1)
   void Register () throws InterruptedException {
        driver.get("https://demoqa.com/books");

        home.clickLogin();
        registration.scrolling();
        registration.clickEnterNUser();
        registration.enterFirstname("MO");
        registration.enterLastname("El");
        registration.enterUsername("MOEL");
        registration.enterPassword("MOEL5@moel5@");
        registration.scrolling();
        registration.clickonrecaptcha();
        registration.clickonregister();

   }
    @Test (priority = 2)
    void login () throws InterruptedException
    {
        driver.get("https://demoqa.com/books");
		home.clickLogin();
		login.enterUsername("MOEL");
		login.enterPassword("MOEL5@moel5@");
        registration.scrolling();
        Thread.sleep(300);
		login.clickLogin();
		Thread.sleep(300);
		System.out.println("The page heading is --- " +dashboard.getHeading());
        driver.quit();
    }
    @Test (priority = 3)
    void data () throws IOException {
        driver.manage().window().setSize(new Dimension(600,600));
        sheet.Done(); }
    @Test (priority = 3)
    void dataaa1 () throws IOException {
        driver.manage().window().setSize(new Dimension(600,600));
        sheet.Done(); }

}
