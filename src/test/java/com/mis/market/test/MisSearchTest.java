package com.mis.market.test;

import java.util.concurrent.TimeUnit;

import com.mis.market.pages.MisSearchPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import Basic.BasicDriver;
import Basic.Constant;
import Basic.Log;
import Basic.Login;

public class MisSearchTest extends BasicDriver {

    public static WebDriver driver;

  
    @Test
    public void misSearchTest() throws Exception {
       

        MisSearchPage mst = new MisSearchPage(driver);
        mst.mis().click();//展开mis模块
        mst.search().click();//进入搜索页
        mst.insertHotq_btn().click();//添加

        mst.insertHotq("自动化测试");//添加热搜词
        mst.HotqText();//取最后一行的搜索文案
        mst.checkHotqInsert();//检查热搜词是否添加成功

        mst.deleteHotq();//删除热搜词
        mst.checkHotqDelete();//检查热搜词是否删除成功

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
		driver.quit();
		Log.info("MisSearchTest页面测试结束");
	}
}