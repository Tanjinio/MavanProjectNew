package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class LogInTest extends TestBase{

	@Test
	public void logInTest() throws InterruptedException{
		
		//System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("inside login class");
		driver.findElement(By.xpath(OR.getProperty("bml_btn"))).click();
		Thread.sleep(5000);
		System.out.println("log in");
		log.debug("successfully clicked on the login button");
		//Reporter.log("successfully clicked on the login button");
		//to add screenshot from pc
		//Reporter.log("<a href=\"C:\\Users\\User\\RahulClass\\aroraerror.png\">Screenshot</a>");
		//will open screenshot in new tab
		//Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\User\\RahulClass\\aroraerror.png\">Screenshot</a>");
		
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addcus_btn"))),"this element is not found");
		
	}
}
