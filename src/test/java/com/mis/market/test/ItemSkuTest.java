package com.mis.market.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemSkuPage;

import Basic.Constant;
import Basic.ExcelWorkBook;
import Basic.Log;
import Basic.Login;

public class ItemSkuTest {
	
	public static WebDriver driver;

	
	@Test
	public static void itemSkuTest() throws Exception{
//		BasicDriver.open();
//		BasicDriver.login();
		
		ItemSkuPage itemsku = new ItemSkuPage(driver);
		itemsku.insert_btn().click(); //新增商品按钮点击
		itemsku.insertListCheck(); //check新增商品页面
		
		//从excel里取sku_id
		ExcelWorkBook excelbook = new ExcelWorkBook();
		try{
			List<String> sku_list = excelbook.readSkuId(Constant.TestDataExcelFilePath);
			int sku_size = sku_list.size();
			for(int i=0;i<sku_size;i++){
				String item_id = sku_list.get(i);
				itemsku.insertSearchCheck(item_id);//check新增商品搜索
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*
		 *check状态筛选
		 */
//		for(int j=2;j>=0;j--){
//			itemsku.filterCheck(j);
//		}
//		
		
		itemsku.filterCheck("新建");
		itemsku.filterCheck("已编辑");
		itemsku.filterCheck("所有");
		itemsku.filterStatus();//check状态筛选文案
		itemsku.listCheck();//check商品列表数量
		
		//从excel里取name
		try{
			List<String> name_list = excelbook.readName(Constant.TestDataExcelFilePath);
			int name_size = name_list.size();
			for(int k=0;k<name_size;k++){
				String name = name_list.get(k);
				itemsku.itemSearchCheck(name);//check商品列表搜索
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		/*
		 *check分类筛选
		 */
		itemsku.category().click();//分类筛选点击
		itemsku.moveToCategory();//鼠标移动到一级分类
		itemsku.categoryCheck();//check二级分类筛选			
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Login.execute(driver,Constant.UserName,Constant.PassWord);
		
		 
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains("退出"));

	}

	@AfterMethod
	public void afterMethod() {
		Log.info("ItemSkuTest");
		driver.quit();
	}
	
}
