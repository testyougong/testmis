package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.mis.market.pages.MisRecomcatPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.BasicDriver;
import Basic.Constant;
import Basic.Log;
import Basic.Login;

public class MisRecomcatTest extends BasicDriver {

	public static WebDriver driver;

  
    @Test
    public void misRecomcatTest() throws Exception {
        

        MisRecomcatPage recomcat = new MisRecomcatPage(driver);
        recomcat.mis().click();//展开分类
        recomcat.recomcat().click();//进入分类商品推荐
        recomcat.insertCat_btn().click();//点击添加分类按钮
        recomcat.insertCat("001024","自动化测试");//填写分类id和分类名称
        recomcat.save_btn().click();//保存并更新
        recomcat.accept_btn();//二次确认
        recomcat.checkInsert("001024","自动化测试");//检验要填写的分类id和分类名称,和insertCat中的数据一致
        recomcat.view().click();//查看详情
        recomcat.insertItem_btn().click();//点击添加商品按钮
        recomcat.insertItem();//添加商品
        recomcat.deleteCat();//删除分类
        recomcat.save_btn().click();//保存并更新
        recomcat.checkDelete("001024","自动化测试");//检测已删除的分类id和分类名称
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
		Log.info("misRecomcatTest页面测试结束");
	}
}


