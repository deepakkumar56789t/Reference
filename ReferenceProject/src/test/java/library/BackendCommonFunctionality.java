package library;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class BackendCommonFunctionality {

	static Logger logger = Logger.getLogger(BackendCommonFunctionality.class);
	public static String TC_ID = null;
	public static String currentDateTime = null;
	public static String dashViewScreenshotPath = null;
	public static File dashViewScreenshot = null;
	

	/**********************************************************************************************
	 /*Script Developed by :- Deepak Kumar
      *Script Name :- Dropnet List and Search.
      *Script Type :- Testng
      *Platform:- Minimum requirement java SE7 to run this script
      */
	 //*********************************************************************************************/
	public static void login() throws IOException {
		String username = getProperty("username");
		String password = getProperty("password");
		BrowserInitialization.driver.findElement(ObjectRepository.by_username).sendKeys(username);
		BrowserInitialization.driver.findElement(ObjectRepository.by_password).sendKeys(password);
		BrowserInitialization.driver.findElement(ObjectRepository.by_submit).click();
		//BackendCommonFunctionality.waitForElement(BrowserInitialization.driver, ObjectRepository.by_landingpg, 20);
		System.out.println("<<<---------User Authenticated Successfully--------->>>");
	}
	
	
	public static String makeDirectory(String name) throws IOException

	{
		String sourceFolder = Reporter.folderPath + "\\Screenshots";
		File f = new File(sourceFolder);
		if (!f.exists()) {
			f.mkdir();
			f = new File(sourceFolder + "/" + name);
			f.mkdir();
		} else {
			f = new File(sourceFolder + "/" + name);
			f.mkdir();
		}

		return f.getAbsolutePath();

	}

	/**********************************************************************************************
	 * Method: takeFullScreenScreenshot() Description:This method will capture
	 * the whole screenshot 
	 * 
	 * //
	 *********************************************************************************************/
	// public static void takeFullScreenScreenshot(String name, String
	// file_name) throws Exception {
	// try {
	// Rectangle screenRect = new
	// Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	// BufferedImage capture = new Robot().createScreenCapture(screenRect);
	// ImageIO.write(capture, "png", new File(file_name + "/" + name));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	/**********************************************************************************************
	 * Method: take_screenshot_Time() Description:This method will take
	 * screenshot of browser window only 
	 *********************************************************************************************/
	public static String takeScreenshot(String name, String file_name, String status) throws Exception {
		String path = null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
			LocalDate sysDate = LocalDate.now();
			String formattedDate = dtf.format(sysDate).replace("/", "_");
			TakesScreenshot scrShot = ((TakesScreenshot) BrowserInitialization.driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(file_name + "/" + name + "_" + formattedDate + "_" + status + ".png");
			path = file_name + "/" + name + "_" + formattedDate + "_" + status + ".png";
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	/**********************************************************************************************
	 * Description:This method will read our test data sheet
	 *********************************************************************************************/
	public static void readExcel() throws Exception {
		String userStoryName = UserStoryName.getUSName();
		String FilePath = BackendCommonFunctionality.getProperty("TestDataSheetPath");
		HashMap<String, LinkedHashMap<String, String>> Excel = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		DataObject o = new DataObject();
		File inputWorkbook = new File(FilePath);
		FileInputStream fis = new FileInputStream(inputWorkbook);
		String path = FilePath;
		if (path.toString().endsWith(".xlsx")) {
			XSSFWorkbook w = new XSSFWorkbook(fis);
			XSSFSheet sheet = w.getSheet(userStoryName);
			// XSSFSheet sheet = w.getSheetAt(0);
			int rowcount = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rowcount; i++) {
				LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
				int columncount = sheet.getRow(i).getLastCellNum();
				for (int j = 0; j < columncount; j++) {
					//System.out.println("I:-"+i+"               J:-"+j);
					String columnName = sheet.getRow(0).getCell(j).toString();
					String columnValue = sheet.getRow(i).getCell(j).toString();
					if (columnName.equalsIgnoreCase("TC_ID")) {
						o.setKey(columnValue);
					}
					list.put(columnName, columnValue);
				}
				Excel.put(o.getKey(), list);
			}
			o.setExcelObject(Excel);
			fis.close();
		}

	}

	/**********************************************************************************************
	 * Method: waitForElement() Description:This method will wait until the
	 * passed argument is visible 
	 *********************************************************************************************/
	public static void waitForElement(WebDriver driver, By xpath, int timeTOWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeTOWaitInSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(xpath));

	}

	public static void waitForElementToBeClickable(WebDriver driver, By xpath, int timeTOWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeTOWaitInSec);
		wait.until(ExpectedConditions.elementToBeClickable(xpath));

	}

	/**********************************************************************************************
	 * Method: waitForElement() Description:This method will wait until the
	 * passed argument is enabled 
	 *********************************************************************************************/
	public static void waitForElementE(WebDriver driver, By xpath, int timeTOWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeTOWaitInSec);
		wait.until(ExpectedConditions.elementToBeClickable(xpath));

	}

	/**********************************************************************************************
	 * Description:This method will check whether we need to run test case or
	 * skip.
	 *********************************************************************************************/
	public static boolean checkRunStatus(String TC_ID) throws Exception {
		readExcel();
		String RunStatus = DataObject.getVariable("Run", TC_ID);
		if (RunStatus.equalsIgnoreCase("Yes")) {
			return true;
		} else {
			return false;
		}

	}

	/**********************************************************************************************
	 * Method: ALM_Pass() Description:This method will update ALM 
	 * 
	 *********************************************************************************************/
	// public void ALM_Pass(String TestCaseName) throws ALMServiceException
	// {
	// System.setProperty("jacob.dll.path","C:/Users/RD5038882/Desktop/TestNG_Framework/jacob-1.18-x86.dll");
	// LibraryLoader.loadJacobLibrary();
	// ALMServiceWrapper wrapper = new
	// ALMServiceWrapper("https://conexus.prod.fedex.com:9445/qcbin");
	// wrapper.connect("5212257","Flags196","ESD","ESD_FLAGS");
	// System.out.println("QC Connection Established");
	//
	// wrapper.updateResult("Z-Trash\\TEMP","new",36533,TestCaseName,
	// StatusAs.PASSED);
	// System.out.println("QC Status Updated");
	// wrapper.close();
	// }
	//

	/**********************************************************************************************
	 * Description:This method will read the Property file. 
	 * 
	 *********************************************************************************************/

	public static String getProperty(String Parameter) throws IOException {
		File file = new File("Config//Config.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileInput);
		String Value = prop.getProperty(Parameter);
		return Value;

	}

	/**********************************************************************************************
	 * Description:This method will delete the Index file. 
	 * 
	 *********************************************************************************************/

	public static void deleteDefaultSuite() throws IOException {
		String Source_Folder = getProperty("DefaultSuite_Path");
		File index = new File(Source_Folder);
		if (!index.exists()) {
		} else {
			index.delete();
		}
	}

	/**********************************************************************************************
	 * Description:This method will display the test case status on the console
	 * 
	 *********************************************************************************************/

	public static void displayTestCaseStatus(String message, String status) {
		System.out.println(message + "  : " + status);
	}

	/**********************************************************************************************
	 * Description:This method will take the system date and format it. 
	 *********************************************************************************************/
	public static String currentdtime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		LocalDate localDate = LocalDate.now();
		LocalTime localtime = LocalTime.now();
		String Str_Date = dtf.format(localDate).replace("/", "_");
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss");
		String Str_Time = dtf1.format(localtime).replace(":", "");
		String DateTime = Str_Date + "_" + Str_Time;
		return DateTime;

	}
	
	/**********************************************************************************************
	 * Description:to check String is in Title case.
	 * 
	 *********************************************************************************************/
	public static Boolean upperCaseFirstVerify(String value) {
		
       char[] array = value.toCharArray();
       for(int i = 0 ; i<array.length; i++) {
       	
       	if(i == 0 && (Character.isUpperCase(array[i]))) {
       		return true;
       	}
       	else if(i > 0 && Character.isLowerCase(array[i])) {
       		return true;
       	}else {
       		return false;
       	}
       }
       return false;
   }

 

	public static void displayUSName(String US_Name) {
		System.out.println("<<<------------Executing the US : " + US_Name+"------------>>>");
	}
	
	/**********************************************************************************************
	 * Description:This method will established the data base connection. 
	 *********************************************************************************************/
	
	public static ArrayList<String> dbConnection(String query, int Column_No) throws IOException, ClassNotFoundException, SQLException {
		String DBUrl=BackendCommonFunctionality.getProperty("DBurl");
		String UserName=BackendCommonFunctionality.getProperty("DBusername");
		String password = BackendCommonFunctionality.getProperty("DBpassword");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(DBUrl, UserName, password);
		Statement stmt = con.createStatement();
		ArrayList<String> ar=new ArrayList<String>();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			ar.add(rs.getString(Column_No));
		
		}
		con.close();
		return ar;
		
	}
	
	
	
	
	/**********************************************************************************************
	 * Description:This method will take the system date and format it. 
	 *********************************************************************************************/
	public static String getCurrentDateTime() {
		
		//currentDateTime= "30_May_2018_153141";
		currentDateTime = new SimpleDateFormat("dd-MMMM-yyyy_HH.mm.ss").format(Calendar.getInstance().getTime());
		System.out.println(currentDateTime);
		return currentDateTime;
	}
	

	/**********************************************************************************************
	 * Description:This method will take Screenshot. 
	 *********************************************************************************************/
	public static void takeReportScreenshot() throws IOException {
		BrowserInitialization.intilaize();
		File f = new File(Reporter.reportName);
		BrowserInitialization.driver.navigate().to(f.toURI().toURL());
		WebElement dashBoardElement = BrowserInitialization.driver.findElement(By.xpath("//div[@class='dashboard-view']"));
		File screenshot = ((TakesScreenshot) BrowserInitialization.driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		Point point = dashBoardElement.getLocation();
		int elementWidth = dashBoardElement.getSize().getWidth();
		int elementHeight = dashBoardElement.getSize().getHeight();
		BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);
		ImageIO.write(elementScreenshot, "png", screenshot);
		String resourcesLocation = BackendCommonFunctionality.getProperty("ResourcesPath");
		dashViewScreenshot = new File(resourcesLocation + "\\report-dashview.png");
		FileUtils.copyFile(screenshot, dashViewScreenshot);
		logger.info("Screenshot Captured");
		BrowserInitialization.driver.quit();
	}
	
	
			
}


