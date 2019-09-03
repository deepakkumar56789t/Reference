package library;

import java.io.IOException;
import java.net.InetAddress;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class Reporter {
	static ExtentReports report = null;
	static ExtentTest extentReportLogger;
	public static String folderPath = null;
	public static String reportName = null;
	static String environment = null;

	/**********************************************************************************************
	 * Description:This method will capture the details of the environment in
	 * which the script is executing. Created on : 03/08/2018
	 *********************************************************************************************/
	@SuppressWarnings("deprecation")
	public static void TestSuiteStart(String ResultHTMLFilePath, String UserName) throws IOException {
		report = new ExtentReports(ResultHTMLFilePath, false, NetworkMode.OFFLINE);
		environment = getExecutionEnvironment();
		report.config().reportName("SAM :");
		report.config().reportHeadline("Automation execution report of Service Area Maintanance");
		report.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName()).addSystemInfo("Environment", environment).addSystemInfo("User Name", UserName);
	}

	public static String getExecutionEnvironment() throws IOException {
		environment = BackendCommonFunctionality.getProperty("URL");
		if (environment.contains("L4")) {
			environment = "L4";
		} else if (environment.contains("L3")) {
			environment = "L3";
		} else if (environment.contains("L2")) {
			environment = "L2";
		} else if (environment.contains("L1")) {
			environment = "L1";
		} else {
			environment = BackendCommonFunctionality.getProperty("URL");
		}
		return environment;
	}

	/**********************************************************************************************
	 * Description:This method will close the report. Created on : 03/08/2018
	 *********************************************************************************************/
	public static void TestSuiteEnd() {
		report.flush();
		report.close();

	}

	/**********************************************************************************************
	 * Description:This method will print the data in the Report Created on :
	 * 03/08/2018
	 *********************************************************************************************/

	public static void TestCaseStart(String TestName, String Description) {
		try {
			extentReportLogger = report.startTest(TestName, Description);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**********************************************************************************************
	 * Description:This method will end the test case in the report Created on :
	 * 03/08/2018
	 *********************************************************************************************/

	public static void TestCaseEnd() {

		try {
			report.endTest(extentReportLogger);
			extentReportLogger = null;
		} catch (Exception e) {

		}

	}

	/**********************************************************************************************
	 * Description:This method will provide the status in the report. Created on
	 * : 03/08/2018
	 *********************************************************************************************/

	public static void StepDetails(String status, String stepName, String testDetails, String screenShotPath) {
		try {
			String testCaseDetail = testDetails + "<br>" + extentReportLogger.addScreenCapture(screenShotPath);
			if (status.equalsIgnoreCase("PASS")) {
				extentReportLogger.log(LogStatus.PASS, stepName, testCaseDetail);
			} else if (status.equalsIgnoreCase("FAIL")) {
				extentReportLogger.log(LogStatus.FAIL, stepName, testCaseDetail);
			} else if (status.equalsIgnoreCase("ERROR")) {
				extentReportLogger.log(LogStatus.ERROR, stepName, testCaseDetail);
			} else if (status.equalsIgnoreCase("INFO")) {
				extentReportLogger.log(LogStatus.INFO, stepName, testCaseDetail);
			} else if (status.equalsIgnoreCase("EXCEPTION")) {
				extentReportLogger.log(LogStatus.UNKNOWN, stepName, testCaseDetail);
			} else {
				extentReportLogger.log(LogStatus.INFO, stepName, testCaseDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void intilizeReporter() {
		try {
			String reportPath = BackendCommonFunctionality.getProperty("ReportPath");
			String currentDateTime = BackendCommonFunctionality.getCurrentDateTime();
			folderPath = reportPath + "\\" + "execution-report" + "_" + currentDateTime;
			reportName = folderPath + "\\execution-report_" + currentDateTime + ".html";
			Reporter.TestSuiteStart(reportName, System.getProperty("user.name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void terminateReporter() {
		Reporter.TestSuiteEnd();
	}
}
