package com.mis.market.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.*;

public class MisHomeitemTest extends BasicDriver{

	public static WebDriver driver;
		
	@Test
	public void misHomeitemTest() throws Exception{
		 
		MisHomeitemPage  homeitem = new MisHomeitemPage(driver);
		homeitem.mis().click();//展开mis模块
		homeitem.homeitem().click();//进入单品推荐页
		homeitem.insert_btn().click();//点击添加按钮
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homeitem.insert_item();//添加商品

	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		//System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
//		String firefoxProfileDir = "C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\elp7lkey.default";
//
//		FirefoxProfile profile = new FirefoxProfile(new File(firefoxProfileDir));
//
//		 driver = new FirefoxDriver(profile);
		
		System.setProperty("webdriver.chrome.driver", Constant.ChromeDriverURL);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver,Constant.UserName,Constant.PassWord);

		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		//driver.quit();
		Log.info("MisHomeitemTest页面测试结束");
	}
}