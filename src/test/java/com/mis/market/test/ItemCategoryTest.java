package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemCategoryPage;

import Basic.Login;

public class ItemCategoryTest{
	public static WebDriver driver;
 
	@Test
	public static void itemCategoryTest() throws Exception{
		
		ItemCategoryPage itemcategory = new ItemCategoryPage(driver);
		itemcategory.category_btn().click();//进入品类管理页面
		
		//check筛选
		itemcategory.filterCheck("下架");
		itemcategory.filterCheck("上架");
		itemcategory.filterCheck("所有");
		
		itemcategory.insert_btn().click();//添加一级分类
		itemcategory.cancel_btn().click();//取消
		itemcategory.view_btn().click();//查看一级分类
		itemcategory.insert_btn().click();//添加二级分类
		itemcategory.cancel_btn().click();//取消
		itemcategory.edit_btn().click();//编辑一级分类
		itemcategory.cancel_btn().click();//取消
		
		itemcategory.view_btn().click();//查看二级分类
		itemcategory.edit_btn().click();//编辑二级分类
		itemcategory.cancel_btn().click();//取消
		
	}
	
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver);

		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
