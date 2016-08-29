package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Basic.Login;

public class MisLoginTest {
	 
	public WebDriver driver;
	//String baseUrl="http://qa.market-mis.wmdev2.lsh123.com";
	@Test
	public void testMisLogin() throws Exception{
		//driver.get(baseUrl+"/");
		Login.execute(driver, "admin@lsh123.com", "654321");
		 
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));
		
	}
	@BeforeMethod
	public void beforeMethod(){
		System.setProperty("Webdriver.firefox.bin","c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}

}
