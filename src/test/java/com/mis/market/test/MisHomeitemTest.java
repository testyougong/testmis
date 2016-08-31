package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.BasicDriver;
import Basic.Constant;
import Basic.Login;

public class MisHomeitemTest extends BasicDriver{

	public static WebDriver driver;
		
	@Test
	public void misHomeitemTest() throws Exception{
		 
		MisHomeitemPage  homeitem = new MisHomeitemPage(driver);
		homeitem.mis().click();//展开mis模块
		homeitem.homeitem().click();//进入单品推荐页
		homeitem.insert_btn().click();//点击添加按钮
		homeitem.insert_item();//添加商品

	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver,Constant.UserName,Constant.PassWord);

		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
		System.out.println("MisHomecatTest页面测试结束");
	}
}