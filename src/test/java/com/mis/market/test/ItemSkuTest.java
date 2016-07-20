package com.mis.market.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemSkuPage;

import Basic.BasicDriver;
import Basic.ExcelWorkBook;

public class ItemSkuTest extends BasicDriver{
	public ItemSkuTest() throws IOException {
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
		System.out.println("ItemSku页面测试结束");
	}
	
	@Test
	public static void itemSkuTest() throws Exception{
		BasicDriver.open();
		BasicDriver.login();
		
		ItemSkuPage itemsku = new ItemSkuPage(driver);
		itemsku.insert_btn().click(); //新增商品按钮点击
		itemsku.insertListCheck(); //check新增商品页面
		
		//从excel里取sku_id
		ExcelWorkBook excelbook = new ExcelWorkBook();
		try{
			List<String> sku_list = excelbook.readSkuId("D://GitProsty//testmis//list.xls");
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
		for(int j=2;j>=0;j--){
			itemsku.filterCheck(j);
		}
		itemsku.filterStatus();//check状态筛选文案
		itemsku.listCheck();//check商品列表数量
		
		//从excel里取name
		try{
			List<String> name_list = excelbook.readName("/Users/zhouxin/Documents/workspace/mis/list.xls");
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
	
}
