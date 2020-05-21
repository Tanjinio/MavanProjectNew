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

public class AddCustomerTest extends TestBase{
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void AddCustomerTest(Hashtable<String,String>data) throws InterruptedException
	{
		
		if(!data.get("runmode").equals("Y")){
			
			throw new SkipException("skipped because runmode is no");
		}
		
		click("addcus_btn");
		
		type("frst_nm",data.get("firstname"));
		type("lst_nm",data.get("lastname"));
		type("pst_cd",data.get("postcode"));
		click("add_btn");
		
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		
		
	}
	
	

}
