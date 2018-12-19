package Nopcommerce;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Registration1 {

    protected static WebDriver driver;

    static String expectedRegisterSuccessMessage = "Your registration completed";


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");//Open Website
        driver.findElement(By.linkText("Register")).click();//Open Registration page
        driver.findElement(By.id("gender-male")).click();//Click on Gender mail
        driver.findElement(By.id("FirstName")).sendKeys("Kiran");//Write First Name
        driver.findElement(By.id("LastName")).sendKeys("Narola");//Write last name


        Select dateOfBirthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        //Enter Day in Date of Birth
        dateOfBirthDay.selectByValue("4");
        //Enter month in Date of Birth
        Select dateOfBirthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        dateOfBirthMonth.selectByIndex(4);
        //or
        //dateOfBirthMonth.selectByValue("4");

        //Enter Year in Date of Birth
        Select dateOfBirthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        dateOfBirthYear.selectByVisibleText("1908");

        DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmm");
        //System.out.println(dateFormat);
        Date date = new Date();
        //System.out.println(date);
        String date1 = dateFormat.format(date);
        // System.out.println(date1);
        //Entering email address testautomation@test.com
        driver.findElement(By.id("Email")).sendKeys("testautomation+" + date1 + "@test.com");
        //Entering Password: myname
        driver.findElement(By.id("Password")).sendKeys("myname");
        //Conforming Password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("myname");
        //clicking on Registration Button
        driver.findElement(By.id("register-button")).click();
        //Comparing Result:
        String actualRegisterSuccessMessage = driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText();
        Assert.assertEquals("Registration successful message is not displayed or registration is failed", expectedRegisterSuccessMessage, actualRegisterSuccessMessage);
        //Click in on Log out button:
        driver.findElement(By.linkText("Log out")).click();
        //closing open windows
        driver.quit();


    }

    @Test
    public void verifyRegistration() {
        /*https://demo.nopcommerce.com/
      click on Electronics - cell phones - HTC One Mini Blue - email a friend -
      fill up required details and click on Send Email button - and
      verify "Only registered customers can use email a friend feature" message*/
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

        driver = new ChromeDriver();
        //Entering wait 30 sec
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Browser Maximising
        driver.manage().window().fullscreen();
        //Opening Website
        driver.get("https://demo.nopcommerce.com/");
        //clicking on Electronics
        driver.findElement(By.xpath("//a[@title=\"Show products in category Electronics\"][contains(text(),'Electronics')]")).click();
        //clicking on Cell Phones
        driver.findElement(By.linkText("Cell phones")).click();
        //clicking on HCT One Mini Blue phone
        driver.findElement(By.linkText("HTC One Mini Blue")).click();
        //clicking on Email a friend for HCT One Mini Blue
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();
        //Entering Friends Email Address
        driver.findElement(By.id("FriendEmail")).sendKeys("kp@yahoo.com");
        //Entering my Email Address
        driver.findElement(By.id("YourEmailAddress")).sendKeys("hp@yahoo.com");
        //Entering Personal message
        driver.findElement(By.id("PersonalMessage")).sendKeys("Please Check");
        //Clicking on Send Email button
        driver.findElement(By.xpath("//input[@name=\"send-email\"]")).click();
        String expectedErrorMessage = "Only registered customers can use email a friend feature";
        String actualdErrorMessage = driver.findElement(By.xpath("//li[contains(text(),'Only registered customers can use email a friend feature')]")).getText();
        Assert.assertEquals("Displayed message is not as expected, test is failed", expectedErrorMessage, actualdErrorMessage);
        //closing browser
        driver.quit();


    }

    @Test
    /*on "https://demo.nopcommerce.com/" when User change price in Euro Product Price should be in Euro Verify any product*/
    public void verifyCurrency() {
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");
        //Variable Declaration
        String ExpectedResult = "Ђ1548.00";
        driver = new ChromeDriver();
        //Entering waite
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Window in full screen
        driver.manage().window().fullscreen();
        //Opening Website
        driver.get("https://demo.nopcommerce.com/");
        //Click on customer currency and select euro
        driver.findElement(By.id("customerCurrency")).findElement(By.xpath("//option[contains(text(),'Euro')]")).click();
        //comparing auto change in euro currency
        driver.findElement(By.xpath("//img[@alt=\"Picture of Apple MacBook Pro 13-inch\"]")).click();
        String ActualResult = driver.findElement(By.xpath("//span[@itemprop=\"price\"]")).getText();

        //result verification
        Assert.assertEquals("currency scenario failed", ExpectedResult, ActualResult);
        //closing browser
        driver.quit();

    }
    
}



