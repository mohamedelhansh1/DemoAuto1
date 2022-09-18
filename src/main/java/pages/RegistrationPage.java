package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationPage {
    WebDriver driver;

    //Constructor that will be automatically called as soon as the object of the class is created
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    By NewUser = By.id("newUser");
    By FName = By.id("firstname");
    By LName = By.id("lastname");
    By UserName = By.id("userName");
    By Password = By.id("password");
    By recaptcha = By.id("g-recaptcha");
    By register = By.id( "register");
    public void clickEnterNUser() {driver.findElement(NewUser).click();}
    public void enterFirstname(String firstname) {driver.findElement(FName).sendKeys(firstname);}
    public void enterLastname(String lastname) {
        driver.findElement(LName).sendKeys(lastname);
    }
    public void enterUsername(String userName) {
        driver.findElement(UserName).sendKeys(userName);
    }
    public void enterPassword(String password) {
        driver.findElement(Password).sendKeys(password);
    }

    public void clickonrecaptcha()
    {

        if(!driver.findElement(recaptcha).isSelected())
// to check the checkbox is already selected or not
        {   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(recaptcha).click();   // Select the checkbox
        }
    }

    public void clickonregister() {driver.findElement(register).click();}

    public void scrolling(){
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,250)", "");
   }

}
