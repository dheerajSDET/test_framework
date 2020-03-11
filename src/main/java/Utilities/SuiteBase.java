package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import aes.reports.*;
import aes.common.*;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import pageobjects.*;

public class SuiteBase {
	
	public static WebDriver driver;
	public static WebElement Element =null;
	public static Logger Add_Log = null;
	public static Properties Param = null;
	public static Properties Project = null;
	public static WebDriver ExistingchromeBrowser;
	public static WebDriver ExistingmozillaBrowser;
	public static WebDriver ExistingIEBrowser;
	public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public static Reports Report;
	Home_page HP = new Home_page();
	
	
	
	public SuiteBase(){
		
		
		if(driver==null){
			
			this.driver=driver;
			this.ExistingchromeBrowser=ExistingchromeBrowser;
			this.ExistingmozillaBrowser=ExistingmozillaBrowser;
			this.ExistingIEBrowser=ExistingIEBrowser;
			
		}
		
		
	}

	
	
	
	public void init() throws IOException{
		//To Initialize logger service.
		Add_Log = Logger.getLogger("rootLogger");
		
		//Create object of Java Properties class
		Param = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//property//Param.properties");
		
		Param.load(fip);
		Add_Log.debug("Param.properties file loaded successfully.");		
	}
	
	
//	public void updatepropertiesfile (String x,String y) throws IOException{
//		Project = new Properties();
//		File file = new File(System.getProperty("user.dir")+"//src//main//java//property//project.properties");
//		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//property//Project.properties");
//		Project.load(fip);
//		FileOutputStream obj = new FileOutputStream(file);
//		Project.setProperty(x,y);
//        Project.store(obj, "Update data into file ");
//		
//	}
	

	
	
	
	
	public void loadWebBrowser() throws MalformedURLException{
		//Check If any previous webdriver browser Instance Is exist then run new test In that existing webdriver browser Instance.
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
				driver = ExistingmozillaBrowser;
				//return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
				driver = ExistingchromeBrowser;
				//return;
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
				driver = ExistingIEBrowser;
				//return;
			}		
		
		
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
				//To Load Firefox driver Instance. 
				driver = new FirefoxDriver();
				ExistingmozillaBrowser=driver;
				Add_Log.info("Firefox Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("Chrome")){
				String os = System.getProperty("os.name");
				if(os.contains("Windows")){
					System.out.println("Working on :"+os);
					System.setProperty("webdriver.chrome.driver","./chromedriver_win");
					//System.setProperty("webdriver.chrome.verboseLogging", "true");
				}
				else{
					System.out.println("Working on :"+os);
					System.setProperty("webdriver.chrome.driver","./chromedriver_lin");
					//System.setProperty("webdriver.chrome.verboseLogging", "true");
				}
				//To Load Chrome driver Instance.
//				ChromeOptions options = new ChromeOptions();
//		        options.addArguments("--headless");
//		        options.addArguments("--no-sandbox");
//		        options.addArguments();
//
//		        options.setAcceptInsecureCerts(true);
//		        options.addArguments("--window-size=1280x800");
//		        options.addArguments("--start-maximized");
		        
		        
		        //chromeOptions.addArguments();
		        //chromeOptions.addArguments("--disable-popup-blocking");
		        //ChromeOptions.addArguments("--ignore-certificate-errors");
		        //chromeOptions.setExperimentalOption("useAutomationExtension", false);
		        //chromeOptions.setExperimentalOption("AcceptInsecureCerts", true);
		        //chromeOptions.setAcceptInsecureCerts(true);
		        //ChromeDriver driver = new ChromeDriver(chromeOptions);
			    
			



		
//			ChromeOptions options = new ChromeOptions();
//			 
//				options.addArguments("headless");
		        
		        
		        
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("--js-flags=--expose-gc");
				options.addArguments("--enable-precise-memory-info");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--disable-default-apps");
				options.addArguments("--enable-automation");
				options.addArguments("test-type=browser");
				options.addArguments("disable-infobars");
				options.addArguments("disable-extensions");
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
				ExistingchromeBrowser= driver;
				Add_Log.info("Chrome Driver Instance loaded successfully.");
				
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
				//To Load IE driver Instance.
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//main//resources//Browser//IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				ExistingIEBrowser=driver;
				Add_Log.info("IE Driver Instance loaded successfully.");
				
				
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			//Resize the current window to the given dimension
			 
			driver.manage().window().maximize();	

			}
	
	
	
	
	
	
public static By getLocator(String locatorType, String locatorValue)   {
		
		if(locatorType.equalsIgnoreCase("id")){
    	 return By.id(locatorValue);
    	 
		}
     
        else if(locatorType.equalsIgnoreCase("xpath")){
       	 return By.xpath(locatorValue);
       	
   		}
        else if(locatorType.equalsIgnoreCase("name")){
         	 return By.name(locatorValue);
     		}
        else if(locatorType.equalsIgnoreCase("class")){
        	return By.className(locatorValue);
      		}
        else if(locatorType.equalsIgnoreCase("tagname")){
        	return By.tagName(locatorValue);
     		}
        else if(locatorType.equalsIgnoreCase("linktext")){
        	return By.linkText(locatorValue);
     		}
        else if(locatorType.equalsIgnoreCase("partiallinktext")){
         	 return By.partialLinkText(locatorValue);
         	
     		}
        else if(locatorType.equalsIgnoreCase("css")){
         	 return By.cssSelector(locatorValue);
         	
     		}
        else {
        	return By.xpath(locatorValue);
        }
        
    }

public static WebElement findElement(String locatorType, String locatorValue){
	
	return	driver.findElement(getLocator(locatorType, locatorValue));
}



//All Frame work Related Functions are below:
	
	


	public void implicitwaiting(int x){
		driver.manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
	}
	
	

	public void mouseOver(WebElement ele){            							// Mouseover an element
		Actions actObj=new Actions(driver);
		actObj.moveToElement(ele).build().perform();
		
		}

	
	public void actionsClick(WebElement ele){    								// Click using Actions Class
		Actions actObj=new Actions(driver);
		actObj.click(ele).build().perform();
		}
	
	
	public void jsClick(WebElement Ele){											//JavaScript Click
		
		JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
		myExecutor.executeScript("arguments[0].click();", Ele);
	}

	public void waitForElementVisible(String locatorType, String locatorValue){     			//Wait for Element to be visible on screen
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorType, locatorValue)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorType, locatorValue)));
		
	}

	public void waitForElementClickable(String locatorType, String locatorValue){    			//Wait for Element to be clickable on screen
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(getLocator(locatorType, locatorValue)));
		
	}

	public void waitForElementPresent(String locatorType, String locatorValue){					//Wait for Element to be present on screen
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorType, locatorValue)));
		}
	
	
	
	public static void dropDownByValue(WebElement element,String value) {						//Select in dropdown

		try {
			Select dropdown=new Select(element);
			dropdown.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void dropDownByIndex(WebElement element,int index)
	{
		try {
			Select dropdown=new Select(element);												//Select in dropdown
			dropdown.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void dropDownByVisibleText(WebElement element,String visibletext)
	{
		try {
			Select dropdown=new Select(element);												//Select in dropdown
			dropdown.selectByVisibleText(visibletext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void logtime(){
	    final JavascriptExecutor js = (JavascriptExecutor) driver;
	    // time of the process of navigation and page load
	    double loadTime = (Double) js.executeScript(
	        "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
	    System.out.print(loadTime + " seconds"); // 5.15 seconds
		
		
	}
	
	public static String takeScreenshot(String screenshotName) throws Exception {
		String snapPath = "";
		try {
			snapPath = "Screenshot-" + screenshotName +  ".png";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(snapPath));

		} catch (Exception e) {
			Reports.addLogs("error", e.getMessage());
		}

		return snapPath;
	}
	

	
	
	public void closeWebBrowser(){
		driver.quit();
		
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE){
			try {
				Reports.addLogs("fail", "Failed page snapshot", result.getName());
				Reports.endTest();
				System.out.println(System.getProperty("user.dir").toString());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}}
		else{
			
			{
				Reports.addLogs("pass", result.getName());
			}
			Reports.endTest();
		}
			
			
		}
	
	@BeforeSuite
	public void beforSuite() throws IOException, SQLException {
		Reports.intializeReport();
		init();
		loadWebBrowser();
		driver.get(Param.getProperty("URL"));

	}

	@AfterSuite
	public void afterSuite() throws SQLException {
		Reports.endReport();

		 closeWebBrowser();

	}

	

}
	
	
