package main.NewUserStories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class ParalellTestCases {
 
	@Test()
	public void MyFirstRun(){
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		System.out.println("<<<------------------Opening Chrome Browser------------------->>>");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.w3schools.com/xml/xpath_axes.asp");
	}
	@Test()
	public void MySecondRun(){
		System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer.exe");
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
		WebDriver driver=new InternetExplorerDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.w3schools.com/xml/xpath_axes.asp");
	}
	@Test()
	public void MyThirdRun(){
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		System.out.println("<<<------------------Opening Chrome Browser------------------->>>");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.w3schools.com/xml/xpath_axes.asp");
	}
}
