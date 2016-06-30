package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemExceptionPage;

import Basic.BasicDriver;

public class ItemExceptionTest extends BasicDriver{
	public ItemExceptionTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void setUp() throws Exception{
		//System.setProperty("webdriver.chrome.driver", "/Users/zhouxin/Desktop/chromedriver"); 
		driver = new FirefoxDriver();
		navigation = driver.navigate();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		System.out.println("ItemException页面测试结束");
	}
	
	@Test
	public static void itemExceptionTest() throws Exception{
		BasicDriver.open();
		BasicDriver.login();
		
		ItemExceptionPage itemexception = new ItemExceptionPage(driver);
		itemexception.exception_btn().click();;//进入在售异常商品页面
		//check筛选
		for(int i=5;i>=0;i--){
			itemexception.filterCheck(i);
		}
		//check物美状态筛选
		for(int j=5;j>=0;j--){
			itemexception.wmstatusCheck(j);
		}
		
	}
}
