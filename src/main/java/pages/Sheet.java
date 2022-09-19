package pages;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Sheet {
    WebDriver driver;
    public Sheet(WebDriver driver) {
        this.driver = driver;
    }
String dir = "D:\\DemoAuto\\Data2.xlsx";

    public void Done() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Create an object of File class to open xls file
        File file = new File(dir);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        XSSFWorkbook WB = new XSSFWorkbook(inputStream);

        //creating a Sheet object
        XSSFSheet sheet=WB.getSheetAt(0);

        //get all rows in the sheet
        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();



        //Navigate to the URL
        driver.get("https://demoqa.com/automation-practice-form");


        //Identify the WebElements for the student registration form
        WebElement firstName=driver.findElement(By.id("firstName"));
        WebElement lastName=driver.findElement(By.id("lastName"));
        WebElement email=driver.findElement(By.id("userEmail"));
        WebElement genderMale= driver.findElement(By.id("gender-radio-1"));
        WebElement mobile=driver.findElement(By.id("userNumber"));
        WebElement address=driver.findElement(By.id("currentAddress"));
        WebElement submitBtn=driver.findElement(By.id("submit"));



        //iterate over all the rows in Excel and put data in the form.
        for(int i=1;i<=rowCount;i++) {
            //Enter the values read from Excel in firstname,lastname,mobile,email,address
            firstName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            lastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            email.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
            js.executeScript("arguments[0].click();", genderMale);
            int number =(int) Math.abs(sheet.getRow(i).getCell(4).getNumericCellValue());
            mobile.sendKeys(String.valueOf(number));
            address.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());

            //Click on the gender radio button using javascript

            //Click on submit button
            js.executeScript("window.scrollBy(0,400)", "");

            submitBtn.click();

            //Verify the confirmation message
            WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

            //create a new cell in the row at index 6
            XSSFCell cell = sheet.getRow(i).createCell(6);

            //check if confirmation message is displayed
            if (confirmationMessage.isDisplayed()) {
                // if the message is displayed , write PASS in the excel sheet
                cell.setCellValue("PASS");

            } else {
                //if the message is not displayed , write FAIL in the excel sheet
                cell.setCellValue("FAIL");
            }

            // Write the data back in the Excel file
            FileOutputStream outputStream = new FileOutputStream("D:\\DemoAuto\\Data2.xlsx");
            WB.write(outputStream);
//            driver.switchTo().activeElement().submit();
//            //close the confirmation popup
////            WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"closeLargeModal\"]"));
////            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
////            js.executeScript("window.scrollBy(0,600)", "");
////
////            closebtn.click();

//            now i am studying git version control
            //wait for page tgio come back to registration page after close button is clicked
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        //Close the workbook
        WB.close();

        //Quit the driver
        driver.quit();
    }


    }

