package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class CustomListener extends TestBase implements ITestListener{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL,arg0.getName().toUpperCase()+" failed with exception: "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotname));
		
		Reporter.log("<a href="+TestUtil.screenshotname+">Screenshot</a>");
		//will open screenshot in new tab
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\User\\RahulClass\\aroraerror.png\">Screenshot</a>");
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, "skipped");
		
		
		
	}

	public void onTestStart(ITestResult arg0) {
		test=reports.startTest(arg0.getName().toUpperCase());
		System.out.println(TestUtil.isTestRunable(arg0.getName(), excel));
		
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS,arg0.getName().toUpperCase()+" pass");
		
		
	}

}
