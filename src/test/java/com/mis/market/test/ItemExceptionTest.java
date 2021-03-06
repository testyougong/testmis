//package com.mis.market.test;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.mis.market.pages.ItemExceptionPage;
//
//import Basic.BasicDriver;
//
//public class ItemExceptionTest extends BasicDriver{
//	public ItemExceptionTest() throws IOException {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@BeforeClass
//	public void setUp() throws Exception{
//		//System.setProperty("webdriver.chrome.driver", "/Users/zhouxin/Desktop/chromedriver"); 
//		driver = new FirefoxDriver();
//		navigation = driver.navigate();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	}
//	
//	@AfterClass
//	public void tearDown() throws Exception {
//		driver.quit();
//		System.out.println("ItemException页面测试结束");
//	}
//	
//	@Test
//	public static void itemExceptionTest() throws Exception{
//		BasicDriver.open();
//		BasicDriver.login();
//		
//		ItemExceptionPage itemexception = new ItemExceptionPage(driver);
//		itemexception.exception_btn().click();;//进入在售异常商品页面
//		//check筛选
//		for(int i=5;i>=0;i--){
//			itemexception.filterCheck(i);
//		}
//		//check物美状态筛选
//		for(int j=5;j>=0;j--){
//			itemexception.wmstatusCheck(j);
//		}
//		
//	}
//}

package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemExceptionPage;

import Basic.Constant;
import Basic.Log;
import Basic.Login;

public class ItemExceptionTest /* extends BasicDriver */ {
	public WebDriver driver;

	// String baseUrl="http://qa.market-mis.wmdev2.lsh123.com";
	@Test
	public void itemExceptionTest() throws Exception {
		ItemExceptionPage itemexception = new ItemExceptionPage(driver);
		itemexception.exception_btn().click();// 进入在售异常商品页面
		//
		// String[] strs = new String[] {"所有异常商品", "A档异常商品", "B档异常商品", "C档异常商品",
		// "供货价为0商品", "负毛利商品", "供货价异常", "销售价异常",
		// "促销价异常" };
		// check物美状态筛选
		for (int j = 5; j >= 0; j--) {

			itemexception.wmstatusCheck1(j);
		}

		// check筛选

		for (int i = 9; i >= 0; i--) {
			itemexception.filterCheck(i);
			itemexception.isEquals("");// 这儿需要异常状态名称来调用
			switch (i) {
				case 9:
					itemexception.isEquals("风控置售罄商品");
				case 8:
					itemexception.isEquals("促销价异常");
				case 7:
					itemexception.isEquals("销售价异常");
				case 6:
					itemexception.isEquals("供货价异常");
				case 5:
					itemexception.isEquals("负毛利商品");
				case 4:
					itemexception.isEquals("供货价为0商品");
				case 3:
					itemexception.isEquals("C档售罄商品");
				case 2:
					itemexception.isEquals("B档售罄商品");
				case 1:
					itemexception.isEquals("A档售罄商品");
				case 0:
					itemexception.isEquals("所有异常商品");
			}

		}

	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver, Constant.UserName, Constant.PassWord);

		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		Log.info("ItemExceptionTest测试结束");
		driver.quit();
	}
}
