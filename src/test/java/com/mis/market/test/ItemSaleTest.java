package com.mis.market.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemSalePage;

import Basic.BasicDriver;
import Basic.ExcelWorkBook;

public class ItemSaleTest extends BasicDriver{
	public ItemSaleTest() throws IOException {
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
		System.out.println("ItemSale页面测试结束");
	}
		
	@Test
	public void itemSaleTest() throws Exception{
		BasicDriver.open();
		BasicDriver.login();
		
		ItemSalePage itemsale = new ItemSalePage(driver);
		itemsale.sale_btn().click(); //销售管理按钮
		
		/*
		 *check状态筛选
		 */
		for(int j=4;j>=0;j--){
			itemsale.filter(j).click(); //筛选按钮点击
		}

		itemsale.filterCheck();//筛选检查
		itemsale.listCheck();//列表数量检查
		
		//从excel里取sale_id
		ExcelWorkBook excelbook = new ExcelWorkBook();
		try{
			List<String> sale_list = excelbook.readSaleId("D://GitProsty//testmis//list.xls");
			int sale_size = sale_list.size();
			for(int i=0;i<sale_size;i++){
				String sale_id = sale_list.get(i);
				itemsale.saleSearchCheck(sale_id);//check销售列表搜索
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		itemsale.edit_btn().click();
		itemsale.editSalePriceNormal();//编辑价格(正常价格)
		itemsale.editSalePriceException();//编辑价格(异常价格,确认更改)
		itemsale.editSalePriceExceptionEdit();//编辑价格(异常价格,编辑)
		
		/*各种保存、取消、编辑按钮的校验
		 */
		itemsale.edit_btn().click();
		itemsale.cancel_btn().click();
		itemsale.edit_btn().click();
		itemsale.save_btn().click();
		itemsale.alert_check();//check详情保存
		
	}
}
