package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;

import java.awt.Toolkit;
public class BrowserInitialization {

	public static WebDriver driver = null;

	/**********************************************************************************************
	 /*Script Developed by :- Deepak Kumar
     *Script Name :- Dropnet List and Search.
     *Script Type :- Testng
     *Platform:- Minimum requirement java SE7 to run this script
     */
	 //*********************************************************************************************/
	public static void intilaize() {

		try {
			String browser = BackendCommonFunctionality.getProperty("Browser");
			switch (browser) {
			case "Chrome": {
				String driverPath =BackendCommonFunctionality.getProperty("DriverPath");
				System.setProperty("webdriver.chrome.driver", driverPath + "/chromedriver.exe");
				System.out.println("<<<------------------Opening Chrome Browser------------------->>>");
				driver = new ChromeDriver();
				openApplication();
				break;
			}
			case "IE": {
				String driverPath = BackendCommonFunctionality.getProperty("DriverPath");
				System.setProperty("webdriver.ie.driver", driverPath + "/IEDriverServer.exe");
				System.out.println("<<<------------------Opening Internet Explorer Browser------------------->>>");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("nativeEvents", false);
				cap.setCapability("unexpectedAlertBehaviour", "accept");
				cap.setCapability("ignoreProtectedModeSettings", true);
				cap.setCapability("disable-popup-blocking", true);
				cap.setCapability("enablePersistentHover", true);
				cap.setCapability("ignoreZoomSetting", false);
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.merge(cap);
				driver = new InternetExplorerDriver(options);
				openApplication();
				break;
			}

			case "FireFox": {
				String driverPath = BackendCommonFunctionality.getProperty("DriverPath");
				System.setProperty("webdriver.firefox.marionette", driverPath + "FirefoxDriverServer.exe");
				System.out.println("<<<------------------Opening FireFox Browser------------------->>>");
				driver = new FirefoxDriver();
				openApplication();
				break;

			}
			default:
				System.out.println("No Suitable Browser's Driver found for the specified Browser ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**********************************************************************************************
	 * Description:This method will maximize the browser window.
	 *********************************************************************************************/
		public static void openApplication() {
			try {
				driver.manage().window().maximize();
				String url = BackendCommonFunctionality.getProperty("URL");
				driver.navigate().to(url);
			} catch (Exception e) {
				e.printStackTrace();

			}

	}
}
