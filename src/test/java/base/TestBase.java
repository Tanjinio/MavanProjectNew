package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;



public class TestBase {
         public static WebDriver driver;
     	public static Properties config=new Properties();
		public static Properties OR=new Properties();
		public static FileInputStream fis;
		public static Logger log=Logger.getLogger(TestBase.class.getName());
		public static WebDriverWait wait;
		public static ExcelReader excel= new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\Excel.xlsx");
		public ExtentReports reports= ExtentManager.getInstance();
		public static ExtentTest test;
		public static String browser;
	
	
	@BeforeSuite
	public void setUp(){
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		
			//FileInputStream fis;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        try {
				config.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	        try {
				fis=new FileInputStream((System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        if(System.getenv("browser") !=null && !System.getenv("browser").isEmpty()){
	        	browser=System.getenv("browser");
	        } 
	        else{
	        	browser=config.getProperty("browser");
	        }
	        
	        config.getProperty("browser", browser);
	        
	        
	        if(config.getProperty("browser").contains("chrome")){
	        	System.setProperty("webdriver.chrome.driver",(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe"));
	             driver=new ChromeDriver();
	             log.debug("chrome browser opened");
	        }
	        else if(config.getProperty("browser").contains("edge")){
	        	System.setProperty("webdriver.edge.driver", "C:\\SeleniumDriver\\edgedriver_win64\\msedgedriver.exe");
	    		driver = new EdgeDriver();
	        }
	        
	        driver.get(config.getProperty("url"));
	        log.debug("successfully hit the app");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWAit")), TimeUnit.SECONDS);
	        wait = new WebDriverWait(driver, 5);
		}
	
	
	    public void click(String locator){
	    	driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
	    	test.log(LogStatus.INFO, "clicking on: "+ locator);
	    }
	    
	    public void type(String locator, String value){
	    	driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
	    	test.log(LogStatus.INFO, "typing the value: "+ value);
	    }
	    
	    static WebElement dropdown;
	    
	    public void select(String locator, String value){
	    	dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
	    	
	    	Select select=new Select(dropdown);
		    select.selectByVisibleText(value);
	    }
	    
	    
	    
	    
	    
			
		public boolean isElementPresent(By by){
			
			try{
				driver.findElement(by);
				return true;
			}
			catch(NoSuchElementException e){
				return false;
			}	
			
			
			
		}
	
	@AfterSuite
    public void tearDown(){
		driver.close();
		log.debug("browser closed");
		reports.flush();
		
	}
}
