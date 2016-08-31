package com.mis.market.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Basic.Constant;
import Basic.properties;

public class ItemSkuPage {
	private WebElement element = null;
	private WebDriver driver = null;
	private properties objectMap = new properties(Constant.propertiesFilePath);// 配置文件相对路径
	
	public ItemSkuPage(WebDriver driver) {
		this.driver = driver;
		//
	}

	// 添加商品按钮
	public WebElement insert_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.addsku_btn"));
		return element;
	}

	// 检查商品列表
	public void insertListCheck() throws Exception {
		Assert.assertTrue("添加商品页面有问题",
				driver.findElement(objectMap.getLocator("mis.ItemSkuPage.skulist_href")).getText().equals("全部商品列表"));
		Thread.sleep(1000);
	}

	// 添加商品搜索检查
	public void insertSearchCheck(String item_id) throws Exception {
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.search_text")).sendKeys(item_id);
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.search_btn")).click();
		Thread.sleep(1000);
		String text=driver.findElement(objectMap.getLocator("mis.ItemSkuPage.skuid_text")).getText();
		if (text.contains("该搜索条件下没有对应的数据")) {
			Assert.fail("该搜索条件下没有对应的数据,请检查");
		}else{
		Assert.assertEquals(item_id,
				driver.findElement(objectMap.getLocator("mis.ItemSkuPage.skuid_text")).getText());
		}
		driver.navigate().back();
		driver.navigate().back();
	}

	// 商品状态筛选检查
	public void filterCheck(int n) throws InterruptedException {
		driver.findElement(By.xpath("//li[@data-value=" + n + "]")).click();
		String text = driver.findElement(By.xpath("//li[@data-value=" + n + "]")).getText();
		String listText = driver
				.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div[1]/table/tbody/tr[1]/td[5]"))
				.getText();
		if (text.contains("所有")) {
			Assert.assertTrue("'所有'筛选错误", listText.equals("已编辑") || listText.equals("新建"));
		} else {
			Assert.assertTrue(text + "筛选错误", text.contains(listText));
		}
		Thread.sleep(1000);
	}
	
	
	public void filterCheck(String str) throws Exception {
		if (str == "所有") {
			driver.findElement(objectMap.getLocator("mis.ItemSkuPage.total_btn")).click();
			//String text = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.total_btn")).getText();
			String listText = driver
					.findElement(objectMap.getLocator("mis.ItemSkuPage.stat_text"))
					.getText();
			//Assert.assertTrue("'所有'筛选错误", listText.equals("已编辑") || listText.equals("新建"));
			Assert.assertTrue(listText.equals("已编辑") || listText.equals("新建"));
			Thread.sleep(1000);

		} else if (str == "已编辑") {
			driver.findElement(objectMap.getLocator("mis.ItemSkuPage.edit_btn")).click();
			//String text = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.edit_btn")).getText();
			String listText = driver
					.findElement(objectMap.getLocator("mis.ItemSkuPage.stat_text"))
					.getText();             
			Assert.assertTrue(listText.equals("已编辑"));
			Thread.sleep(1000);
		} else {
			driver.findElement(objectMap.getLocator("mis.ItemSkuPage.new_btn")).click();
			//String text = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.new_btn")).getText();
			String listText = driver
					.findElement(objectMap.getLocator("mis.ItemSkuPage.stat_text"))
					.getText();
			Assert.assertTrue(listText.equals("新建"));
			Thread.sleep(1000);
		}
	}

	// 商品列表筛选文案检查
	public void filterStatus() throws Exception {
		//li[@data-value='0']
		Assert.assertTrue("'所有'筛选文案错误", driver.findElement(objectMap.getLocator("mis.ItemSkuPage.total_btn")).getText().contains("所有"));
		//li[@data-value='1']
		Assert.assertTrue("'新建'筛选文案错误", driver.findElement(objectMap.getLocator("mis.ItemSkuPage.new_btn")).getText().contains("新建"));
		Assert.assertTrue("'已编辑'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSkuPage.edit_btn")).getText().contains("已编辑"));
	}

	// 分类筛选
	public WebElement category() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.type_search"));
		return element;
	}

	// 鼠标移动到一级分类
	public void moveToCategory() throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(objectMap.getLocator("mis.ItemSkuPage.one_classification"))).perform();
	}

	// 二级分类筛选检查
	public void categoryCheck() throws Exception {
		String text = driver.findElement(objectMap.getLocator("mis.ItemSkuPage.two_classification")).getText();
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.two_classification")).click();
		String listText = driver
				.findElement(objectMap.getLocator("mis.ItemSkuPage.classification_name"))
				.getText();
		Assert.assertEquals(text, listText);
		Thread.sleep(2000);
	}

	// 商品列表商品数量检查
	public void listCheck() throws Exception {
		List<WebElement> item_list = driver.findElements(objectMap.getLocator("mis.ItemSkuPage.view_btn"));
		Assert.assertEquals(12, item_list.size());
	}

	// 搜索检查
	public void itemSearchCheck(String name) throws Exception {
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.search_text")).sendKeys(name);
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.search_btn")).click();
		Thread.sleep(1000);
		Assert.assertEquals(name, driver.findElement(objectMap.getLocator("mis.ItemSkuPage.skuname_text")).getText());
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.view_btn")).click();
		Thread.sleep(1000);
		Assert.assertEquals(name, driver.findElement(objectMap.getLocator("mis.ItemSkuPage.titlename_text")).getText());
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(objectMap.getLocator("mis.ItemSkuPage.return_btn")).click();// 返回所有搜索结果
		Thread.sleep(1000);
	}
}
