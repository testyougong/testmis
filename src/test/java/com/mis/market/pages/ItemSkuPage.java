package com.mis.market.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ItemSkuPage{
	private WebElement element = null;
	private WebDriver driver = null;

	public ItemSkuPage(WebDriver driver){
        this.driver = driver;
	}
	
	//添加商品按钮
	public WebElement insert_btn(){
		element = driver.findElement(By.linkText("添加商品"));
		return element;
	}
	
	//检查商品列表
	public void insertListCheck() throws InterruptedException{
		Assert.assertTrue("添加商品页面有问题", driver.findElement(By.className("crumb")).getText().equals("全部商品列表"));
		Thread.sleep(1000);
	}
	
	//添加商品搜索检查
	public void insertSearchCheck(String item_id) throws InterruptedException{
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(item_id);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(item_id,driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[4]/table/tbody/tr/td[1]")).getText());
		driver.navigate().back();
		driver.navigate().back();
	}
	
	//商品状态筛选检查
	public void filterCheck(int n) throws InterruptedException{
		driver.findElement(By.xpath("//li[@data-value="+n+"]")).click();
		String text = driver.findElement(By.xpath("//li[@data-value="+n+"]")).getText();
		String listText =  driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div[1]/table/tbody/tr[1]/td[5]")).getText();
		if(text.contains("所有")){
			Assert.assertTrue("'所有'筛选错误",listText.equals("已编辑") || listText.equals("新建"));
		}else{
			Assert.assertTrue(text+"筛选错误",text.contains(listText));
		}
		Thread.sleep(1000);
	}
	
	//商品列表筛选文案检查
	public void filterStatus(){
		Assert.assertTrue("'所有'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='0']")).getText().contains("所有"));
		Assert.assertTrue("'新建'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='1']")).getText().contains("新建"));
		Assert.assertTrue("'已编辑'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='2']")).getText().contains("已编辑"));
	}
	
	//分类筛选
	public WebElement category(){
		element = driver.findElement(By.xpath("//span[@class='text']"));
		return element;
	}
	
	//鼠标移动到一级分类
	public void moveToCategory(){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@data-value='001020']"))).perform();
	}
	
	//二级分类筛选检查
	public void categoryCheck() throws InterruptedException{
		String text = driver.findElement(By.xpath("//a[@data-value='001020001']")).getText();
		driver.findElement(By.xpath("//a[@data-value='001020001']")).click();
		String listText = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div[1]/table/tbody/tr[1]/td[4]")).getText();
		Assert.assertEquals(text,listText);
		Thread.sleep(2000);
	}
	
	//商品列表商品数量检查
	public void listCheck(){
		List<WebElement> item_list= driver.findElements(By.linkText("查看"));
		Assert.assertEquals(12,item_list.size());
	}
	
	//搜索检查
	public void itemSearchCheck(String name) throws InterruptedException{
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(name);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(name,driver.findElement(By.className("wrap")).getText());
		driver.findElement(By.linkText("查看")).click();
		Thread.sleep(1000);
		Assert.assertEquals(name,driver.findElement(By.xpath("//h1[@class='title']")).getText());
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-link search-reset']")).click();//返回所有搜索结果
		Thread.sleep(1000);
	}
}
