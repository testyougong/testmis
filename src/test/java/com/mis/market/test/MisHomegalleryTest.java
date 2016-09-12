package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mis.market.pages.MisHomegalleryPage;

import Basic.BasicDriver;
import Basic.Constant;
import Basic.Log;
import Basic.Login;

public class MisHomegalleryTest {

	public static WebDriver driver;
		
	@Test
	public void misHomegalleryTest() throws Exception{
	 
		MisHomegalleryPage homegallery = new MisHomegalleryPage(driver);
		homegallery.mis().click();//点击mis
		homegallery.homegallery().click();//点击banner模块
		homegallery.insert_btn().click();//添加按钮
		/*添加banner
		 * 前两个数为开始时间和结束时间中日历的列数
		 * 第三个数为banner类型,1为轮播图，2为活动栏
		 * 第四个数为活动类型，1为单品，2为活动，3为公告，4为秒杀
		 * 第五个数为单品id
		 * 第六个数为活动id
		 * 第七个数为公告地址
		 * 第八个数为本地图片路径
		 * */
		homegallery.insert_banner(1,3,2,3,100446,460212,"http://qa.market-h5.dev.lsh123.com/static/home/special/notice20160314/index.html","/Users/zhouxin/Desktop/11475200908374e48ao.jpg");
		homegallery.save_btn().click();//保存banner
		homegallery.save_success();//保存成功提示
		
		/*删除banner
		 * */
		homegallery.delete_banner().click();//删除banner
		homegallery.save_btn().click();//保存banner
		homegallery.save_success();//保存成功提示
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
		Log.info("MisHomegalleryTest页面测试结束");
	}
}
