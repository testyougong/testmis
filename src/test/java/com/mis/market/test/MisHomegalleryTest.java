package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mis.market.pages.MisHomegalleryPage;

import Basic.BasicDriver;

public class MisHomegalleryTest extends BasicDriver{

	public MisHomegalleryTest() throws IOException {
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
		System.out.println("MisHomegallery页面测试结束");
	}
		
	@Test
	public void misHomegalleryTest() throws Exception{
		BasicDriver.open();
		BasicDriver.login();
		
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
	
}
