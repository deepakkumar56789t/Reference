package main.NewUserStories;

import java.io.IOException;

import library.BackendCommonFunctionality;
import library.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WebTable {
 
@Test()
 public void automateWebTable() throws IOException{
	String path=BackendCommonFunctionality.getProperty("DriverPath");
	System.setProperty("webdriver.chrome.driver", path+"/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.get(BackendCommonFunctionality.getProperty("URL"));
	WebDriverWait wait=new WebDriverWait(driver, 60);
	WebElement element=wait.until(ExpectedConditions.elementToBeClickable(ObjectRepository.by_Edit1_Record));
	String user="Karen";
	By elementAction=By.xpath("//div[text()='"+user+"']/../../../div/div[6]/user-click-select/div/del-click/button");
	Actions actions = new Actions(driver);
	WebElement elementLocator = driver.findElement(elementAction);
	actions.contextClick(elementLocator).perform();
	By elementDeletebutton=By.xpath("(//button[text()='Delete'])[2]");
	driver.findElement(elementDeletebutton).click();
    
	}
}
