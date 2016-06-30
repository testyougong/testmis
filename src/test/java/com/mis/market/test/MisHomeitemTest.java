package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.BasicDriver;

public class MisHomeitemTest extends BasicDriver{

	public MisHomeitemTest() throws IOException {
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
		System.out.println("MisHomeitem页面测试结束");
	}
		
	@Test
	public void misHomeitemTest() throws Exception{
		BasicDriver.open();
		BasicDriver.login();
		
		MisHomeitemPage  homeitem = new MisHomeitemPage(driver);
		homeitem.mis().click();//展开mis模块
		homeitem.homeitem().click();//进入单品推荐页
		homeitem.insert_btn().click();//点击添加按钮
		homeitem.insert_item();//添加商品

	}
}