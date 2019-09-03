package main.NewUserStories;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GroupExample {
	
	@Parameters({"author","reciever"})
	
	
	@Test(priority=1 , groups ={"Cars"} , threadPoolSize = 3, invocationCount = 2, timeOut = 1000 )
	public void car1(String author, String reciever){
		System.out.println("This is the cars1 group");
		System.out.println("author-->"+author);
		System.out.println("reciever-->"+reciever);
	}
	@Test(priority=2 ,groups ={"Cars"} , dependsOnGroups={"Scooter"})
	public void car2(){
		System.out.println("This is the cars2 group");
	}
	@Test(priority=3 ,groups ={"Cars"}, dependsOnMethods={"Sedan"})
	public void car3(){
		System.out.println("This is the cars3 group");
	}
	@Test(priority=4 ,groups ={"Cars"} , enabled=false)
	public void car4(){
		System.out.println("This is the cars4 group");
	}
	@Test(groups ={"Scooter"})
	public void scooter1(){
		System.out.println("This is the Scooter1 group");
	}
	@Test(groups ={"Scooter"})
	public void scooter2(){
		System.out.println("This is the Scooter2 group");
	}
	@Test(groups ={"Scooter"})
	public void scooter3(){
		System.out.println("This is the Scooter3 group");
	}
	@Test(groups ={"Sedan"})
	public void Sedan(){
		System.out.println("This is the Sedan group");
	}
}
