package april13;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class NewTest {
	WebDriver driver;
	@BeforeTest
	  public void beforeTest() {
		driver=new FirefoxDriver();
	  }
	
  @Test(dataProvider = "dp")
  public void orangeHrm(String user, String pass) {
	  driver.get("http://orangehrm.qedgetech.com/");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(user);
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(pass);
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  String expected="dashboard";
	  String actual=driver.getCurrentUrl();
	  try{
		  Assert.assertEquals(actual.contains(expected),"true");
	  }catch(Throwable t)
	  {
		  Reporter.log(t.getMessage(),true);
	  }
	  
  }

  @DataProvider
  public Object[][] dp() {
   Object Login[][]=new Object[4][2];
   Login[0][0]="Admin";
   Login[0][1]="Qedge123!@#";
   Login[1][0]="Admin";
   Login[1][1]="Admin";
   Login[2][0]="Admin";
   Login[2][1]="Ad";
   Login[3][0]="Ad";
   Login[3][1]="Admin";
   return Login;
    };
  
  

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
