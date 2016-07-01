package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.mis.market.pages.MisRecomcatPage;
import com.mis.market.pages.MisSearchPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.BasicDriver;

public class MisSearchTest extends BasicDriver {

    public MisSearchTest() throws IOException {
        super();
        // TODO Auto-generated constructor stub
    }

    @BeforeClass
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "/Users/zhouxin/Desktop/chromedriver");
        driver = new FirefoxDriver();
        navigation = driver.navigate();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        System.out.println("MisSearchTest页面测试结束");
    }

    @Test
    public void misSearchTest() throws Exception {
        BasicDriver.open();
        BasicDriver.login();

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
}