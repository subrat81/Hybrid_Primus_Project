package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constants.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean verifyLogin(String username,String password)
{
	driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
	driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
	driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).click();
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("login sucees",true);
		return true;
	}
	else
	{
		Reporter.log("invalid credetials",true);
		return false;
	}
	
}
public static void clickBranch()
{
	driver.findElement(By.xpath(config.getProperty("ObjClickBranches"))).click();
}
public static boolean verifyBranchCreation(String bname,String adress1,String adress2,String adress3,String area,String code,String country,String state,String city)
{
	driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
	driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(bname);
	driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(adress1);
	driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(adress2);
	driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(adress3);
	driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(area);
	driver.findElement(By.xpath(config.getProperty("Objzip"))).sendKeys(code);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(country);
	new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(state);
	new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
	driver.findElement(By.xpath(config.getProperty("Objsubmitbtn"))).click();
	String expectedalert=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualalert="new Branch with";
	if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
	{
		Reporter.log(expectedalert,true);
		return true;
	}
	else
	{
		Reporter.log("branch creation failed",true);
		return false;
	}
		
	
	
}
public static boolean verifyBranchUpdate(String branch,String address,String zip)throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
	Thread.sleep(3000);
	WebElement ObjBranchName =driver.findElement(By.xpath(config.getProperty("ObjBranch")));
	ObjBranchName.clear();
	ObjBranchName.sendKeys(branch);
	Thread.sleep(3000);
	WebElement ObjAddress =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
	ObjAddress.clear();
	ObjAddress.sendKeys(address);
	Thread.sleep(3000);
	WebElement Objzipcode=driver.findElement(By.xpath(config.getProperty("Objzipcode")));
	Objzipcode.clear();
	Objzipcode.sendKeys(zip);
	Thread.sleep(3000);
	driver.findElement(By.xpath(config.getProperty("ObjupdateBtn"))).click();
	String expectedupdateAlert =driver.switchTo().alert().getText();
	Thread.sleep(3000);
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	String actualupdateAlert ="Branch updated";
	if(expectedupdateAlert.toLowerCase().contains(actualupdateAlert.toLowerCase()))
	{
		Reporter.log(expectedupdateAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Updation Fail",true);
		return false;
	}
	
}
public static void clickRoles()throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjClickRoles"))).click();
	Thread.sleep(3000);
}
public static boolean verifyRoleCreation(String rolename,String roledesc,String roletype) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjNewRole"))).click();
	driver.findElement(By.xpath(config.getProperty("ObjRoleName"))).sendKeys(rolename);
	driver.findElement(By.xpath(config.getProperty("ObjRoleDesc"))).sendKeys(roledesc);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjRoleType")))).selectByVisibleText(roletype);
	Thread.sleep(2000);
	driver.findElement(By.xpath(config.getProperty("ObjSbtn"))).click();
	String expectedAlert=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualAlert="New Role with";
	if(expectedAlert.toLowerCase().contains(actualAlert.toLowerCase()))
	{
		Reporter.log(expectedAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Role creation failed",true);
		return false;
	}
	
}
public static boolean verifyRoleUpdation(String rolename,String desc, String roletype)
{
	driver.findElement(By.xpath(config.getProperty("ObjEdit1"))).click();
	WebElement RoleN=driver.findElement(By.xpath(config.getProperty("ObjRole")));
	RoleN.clear();
	RoleN.sendKeys(rolename);
	WebElement RDesc=driver.findElement(By.xpath(config.getProperty("ObjRoleD")));
	RDesc.clear();
	RDesc.sendKeys(desc);
	
	new Select(driver.findElement(By.xpath(config.getProperty("ObjRoleT")))).selectByVisibleText(roletype);
	driver.findElement(By.xpath(config.getProperty("Objupdate"))).click();
	String expectedupdateAlert=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualupdateAlert="Role updated ";
	if(expectedupdateAlert.toLowerCase().contains(actualupdateAlert.toLowerCase()))
	{
		Reporter.log(expectedupdateAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Role Updation Fail",true);
		return false;
	}
	
}
public static boolean verifyLogout()throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
	Thread.sleep(3000);
	if(driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).isDisplayed())
	{
		Reporter.log("Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
}
}

