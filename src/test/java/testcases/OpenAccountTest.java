package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String>data) throws InterruptedException{
		
		if(!TestUtil.isTestRunable("openAccountTest", excel))
			throw new SkipException("skipping the test " + "openAccountTest".toUpperCase());
		
        click("open_account");
		select("customer",data.get("customer"));
		select("currency",data.get("currency"));
		click("process");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
	}
	
	


}
