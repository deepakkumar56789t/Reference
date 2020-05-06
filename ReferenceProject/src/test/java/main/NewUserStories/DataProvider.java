package main.NewUserStories;

import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DataProvider {
	@org.testng.annotations.DataProvider(name="test1")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "Guru", "India" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };

    }

@Test(dataProvider ="test1")
	private void test1(String username, String pass) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.navigate().to("http://demo.automationtesting.in/WebTable.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("USER NAME :-"+username);
		System.out.println("Password :-"+pass);
        Thread.sleep(10000);
	}
}

/*public Iterator<Object[]> terator;
public HashMap<String, String> myhashMap=new HashMap<String, String>();
@org.testng.annotations.DataProvider(name="test1")
public Iterator<Object[]> myFirstMethod(){
	myhashMap.put("983598","983598");0
	myhashMap.put("983598", "983598");
	//terator = ( (Object) myhashMap).iterator(); 
	return terator;
			  

}*/
