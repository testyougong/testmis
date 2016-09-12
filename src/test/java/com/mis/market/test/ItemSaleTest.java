package com.mis.market.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.ItemSalePage;

import Basic.Constant;
import Basic.ExcelWorkBook;
import Basic.Log;
import Basic.Login;

public class ItemSaleTest {
	public WebDriver driver;

	@Test
	public void itemSaleTest() throws Exception {

		ItemSalePage itemsale = new ItemSalePage(driver);
		itemsale.sale_btn().click(); // 销售管理按钮

		/*
		 * check状态筛选
		 */
		for (int j = 4; j >= 0; j--) {
			itemsale.filter(j).click(); // 筛选按钮点击
		}

		itemsale.filterCheck();// 筛选检查
		itemsale.listCheck();// 列表数量检查

		// 从excel里取sale_id
		ExcelWorkBook excelbook = new ExcelWorkBook();
		try {
			List<String> sale_list = excelbook.readSaleId(Constant.TestDataExcelFilePath);
			int sale_size = sale_list.size();
			for (int i = 0; i < sale_size; i++) {
				String sale_id = sale_list.get(i);
				itemsale.saleSearchCheck(sale_id);// check销售列表搜索
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		itemsale.edit_btn().click();
		itemsale.editSalePriceNormal();// 编辑价格(正常价格)
		itemsale.editSalePriceException();// 编辑价格(异常价格,确认更改)
		itemsale.editSalePriceExceptionEdit();// 编辑价格(异常价格,编辑)

		/*
		 * 各种保存、取消、编辑按钮的校验
		 */
		itemsale.edit_btn().click();
		itemsale.cancel_btn().click();
		itemsale.edit_btn().click();
		itemsale.save_btn().click();
		itemsale.alert_check("确认保存吗?");// check详情保存

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
		Log.info("ItemSale页面测试结束");
	}
}
