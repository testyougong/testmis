package com.mis.market.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.mis.market.pages.MisRecomcatPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mis.market.pages.MisHomeitemPage;

import Basic.BasicDriver;

public class MisRecomcatTest extends BasicDriver {

    public MisRecomcatTest() throws IOException {
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
        System.out.println("MisRecomcat页面测试结束");
    }

    @Test
    public void misRecomcatTest() throws Exception {
        BasicDriver.open();
        BasicDriver.login();

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
}


