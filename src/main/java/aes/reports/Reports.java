package aes.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.SuiteBase;


public class Reports {
	
	private static ExtentReports report;
	private static ExtentTest logger;
	public static String reportPath;
	
	public static void intializeReport()
	{
//		reportPath="C:\\Users\\djain13\\workspace\\iceautomation\\src\\test\\reports\\AdvanceReport.html";
		reportPath="./AdvanceReport.html";
		report= new ExtentReports(reportPath,true);
		
	}
	
	public static void intializeLoger(String testCase){
		logger=report.startTest(testCase);
	}
	
	public static void addLogs(String status, String value){
		
		switch (status.toLowerCase()) {
		case "info":
			logger.log(LogStatus.INFO, value);
			break;
		case "pass":
			logger.log(LogStatus.PASS, value);
			break;
		case "fail":
			logger.log(LogStatus.FAIL, value);
			break;
		case "skip":
			logger.log(LogStatus.SKIP, value);
			break;
		case "error":
			logger.log(LogStatus.ERROR, value);
			break;
			
		default:
			break;
		}
		
		
	}
	
	public static void addLogs(String status, String value, String snapshotName) throws Exception{
		
		//capturing Snaps and getting image String
		String image =logger.addScreenCapture(SuiteBase.takeScreenshot(snapshotName));
		
		switch (status.toLowerCase()) {
		case "info":
			logger.log(LogStatus.INFO, value, image);
			break;
		case "pass":
			logger.log(LogStatus.PASS, value, image);
			break;
		case "fail":
			logger.log(LogStatus.FAIL, value, image);
			break;
		case "skip":
			logger.log(LogStatus.SKIP, value, image);
			break;
		case "error":
			logger.log(LogStatus.ERROR, value, image);
			break;
			
		default:
			break;
		}
		
		
	}

	public static void endTest(){
		report.endTest(logger);
	}
	
	public static void endReport() {
			report.flush();
	}
	
	public static void getReport(){
		//Utils.driver.get(reportPath);
	}
}



