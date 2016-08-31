package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomecatPage;

import Basic.Constant;
import Basic.Login;

public class MisHomecatTest {
	public static WebDriver driver;
	 

	
	@Test
	public static void misHomecatTest() throws Exception{
//		BasicDriver.open();
//		BasicDriver.login();
		
		MisHomecatPage homecat = new MisHomecatPage(driver);
		homecat.mis().click();//展开mis
		homecat.homecat().click();//定位到品类推荐
		homecat.btn_insert().click();//添加
		/* 添加品类推荐
		 * 第一个数为品类名称
		 * 第二个熟为品类id
		 * 第三个数为图片地址
		 * */
		homecat.insertHomecat("qatest","001024",Constant.TestPicturePath1);
		homecat.homecatCheck("001024");//check列表中有该id的品类
		homecat.btn_save().click();//保存
		homecat.save_success();//保存成功
		
		/*删除品类推荐
		 * */
		homecat.delete_cat().click();//删除品类
		homecat.btn_save().click();//保存
		homecat.save_success();//保存成功
		
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver,Constant.UserName,Constant.PassWord);

		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		//driver.quit();
		System.out.println("MisHomecatTest页面测试结束");
	}
}
