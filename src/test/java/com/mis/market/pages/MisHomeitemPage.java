package com.mis.market.pages;

import Basic.BasicDriver;
import Basic.Constant;
import Basic.properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MisHomeitemPage {
	
	/*这个需要和阻销商品管理做依赖性测试，及要借助它来运行测试*/
	private WebElement element = null;
	private WebDriver driver = null;
	private properties objectMap = new properties(Constant.propertiesFilePath);// 配置文件相对路径

    public MisHomeitemPage(WebDriver driver){
		this.driver = driver;
	}
	
	//mis模块
	public WebElement mis() throws Exception{
		element = driver.findElements(objectMap.getLocator("mis.MisHomeitemPage.mis_btn")).get(6);
		Thread.sleep(1000);
		return element;
	}
			
	//品类推荐
	public WebElement homeitem() throws Exception{
		element = driver.findElement(objectMap.getLocator("mis.MisHomeitemPage.list_btn"));
		return element;
	}

	//添加按钮
	public WebElement insert_btn() throws Exception{
		element = driver.findElement(objectMap.getLocator("mis.MisHomeitemPage.Add_btn"));
		return element;
	}

	//添加商品
    public  void insert_item() throws Exception {
        
         //BasicDriver.windows(1);//切换窗口
    	BasicDriver.switchToWindow_Title(driver,Constant.newWindowUrl);
    	
        driver.findElement(objectMap.getLocator("mis.MisHomeitemPage.sale_btn")).click();
        Thread.sleep(500);
        String name = driver.findElement(objectMap.getLocator("mis.MisHomeitemPage.sku_name_text")).getText();
        driver.findElement(objectMap.getLocator("mis.MisHomeitemPage.Add_btn")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();//现在是取消,回头改成确定
        driver.switchTo().alert().accept();
        
        
        BasicDriver.switchToWindow_Title(driver,Constant.oldwWindowUrl);
        //BasicDriver.windows(0);//返回原窗口
        driver.navigate().refresh();//刷新页面
        List<WebElement> list = driver.findElements(objectMap.getLocator("mis.MisHomeitemPage.view_btn"));
        int position = list.size();
        Assert.assertEquals(name,driver.findElement(By.xpath("//tr["+position+"]/td[2]/span")).getText());
    }

}