package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Basic.Constant;
import Basic.ExcelUtil;
import Basic.Log;
import Basic.Login;

public class TestUtil {

	public WebDriver driver;
	// 调用Constan类中的常量Constant.url
	private String baseUrl = Constant.Url;

	// 定义dataProvider,并命名为testdata
	@DataProvider(name = "testData")
	public static Object[][] data() throws Exception {
		return ExcelUtil.getTestData(Constant.MisTestDataPath, Constant.SheetName);

	}

	@Test(dataProvider = "testData")
	public void testLogin(String CaseRowNumber, String CasName, String UserName, String PassWord,
			String assertUserName)
			throws Exception {
		driver.get(baseUrl);
		try {
			Login.execute(driver, UserName, PassWord);

		} catch (AssertionError error) {
			/*
			 * 执行Login类的execute方法失败时，
			 * catch语句可以捕获AssertionError类型的异常
			 * ，并设置Excel中测试数据执行的执行结果为“测试执行失败”，由于Excel中的序号格式被默认设定为带有一位小数，
			 * 所以使用spilt("[.]")[0]语句获取序号的整数部分，并传给setCellData函数在对应序号的参数数据行的最后一列设定
			 * “测试执行失败”
			 */
			ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]), ExcelUtil.getLastColumnNum(),
					"测试执行失败");
			Assert.fail("执行失败");

		}
		Thread.sleep(3000);
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertUserName));

		} catch (AssertionError error) {
			ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]), ExcelUtil.getLastColumnNum(),
					"测试执行失败");
			Assert.fail("断言mis首页的页面是否包含用户名的关键字失败");
		}

		Thread.sleep(3000);
		try {
			//Assert.assertTrue(driver.getPageSource().contains(assertQuitBtn));

		} catch (AssertionError error) {
			ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]), ExcelUtil.getLastColumnNum(),
					"测试执行失败");
			
			Assert.fail("断言mis页面是否包含退出关键字失败");
		}

 
		
		ExcelUtil.setCellData(Integer.parseInt(CaseRowNumber.split("[.]")[0]),ExcelUtil.getLastColumnNum(), "测试执行成功");
		Log.info("测试结果成功写入测试执行结果列中");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("Webdriver.firefox.bin", "c:\\Program File (X86)\\MozillaFirefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
