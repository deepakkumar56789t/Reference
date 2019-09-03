package main.NewUserStories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.common.primitives.Chars;

public class MyClass {

	@Test( threadPoolSize = 3, invocationCount = 6, timeOut = 1000 )
	public void runWebDriver(){
		
		//Example 1
		/*System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.navigate().to("https://www.w3schools.com/xml/xpath_axes.asp");
		System.out.println(driver.getWindowHandle());
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://devsso.secure.fedex.com/FlagsL3Safe/dropnet");
		System.out.println(driver.getTitle());
		System.out.println(driver.getClass());
		System.out.println(driver.getCurrentUrl());
		driver.quit();*/
		
		
		
		String myString="Sonali";
		char[] ch1=myString.toCharArray();
		List<Character> mylist=(List) Chars.asList(myString.toCharArray());
		Collections.reverse(mylist);
		System.out.println(mylist);
		String reversevalue="";
		for(Object obj: mylist){
			reversevalue=reversevalue+obj;
		}
		System.out.println("Reverse value of the sonali is using List-->"+reversevalue);
		
		
		
		
	}
}
