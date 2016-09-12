package com.mis.market.pages;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.Constant;
import Basic.Log;
import Basic.properties;

public class ItemSalePage {
	private WebElement element = null;
	private WebDriver driver = null;
	//private String propurl = "src//main//java//properties//market.properties";// 配置文件相对路径
	private properties objectMap = new properties(Constant.propertiesFilePath);// 配置文件相对路径

	public ItemSalePage(WebDriver driver) {
		this.driver = driver;
	}

	// 销售管理按钮
	public WebElement sale_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSalePage.SalesMan_btn"));
		Thread.sleep(1000);
		return element;
	}

	// 筛选
	public WebElement filter(int n) throws InterruptedException {
		element = driver.findElement(By.xpath("//li[@data-value=" + n + "]"));
		Thread.sleep(1000);
		return element;
	}

	// 筛选文案检查
	public void filterCheck() throws Exception {
		Assert.assertTrue("'所有'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.total_btn")).getText().contains("所有"));
		Assert.assertTrue("'新建'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.new_btn")).getText().contains("新建"));
		Assert.assertTrue("'下架'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.Theshelves_btn")).getText().contains("下架"));
		Assert.assertTrue("'上架'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.shelves_btn")).getText().contains("上架"));
		Assert.assertTrue("'待生效价格'筛选文案错误",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.effect_btn")).getText().contains("待生效价格"));
	}

	// 销售列表数量检查
	public void listCheck() throws Exception {
		List<WebElement> sale_list = driver.findElements(objectMap.getLocator("mis.ItemSalePage.view_btn"));
		Thread.sleep(1000);
		Assert.assertEquals(12, sale_list.size());
	}

	// 搜索检查
	public void saleSearchCheck(String sale_id) throws Exception {
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.search_text")).sendKeys(sale_id);
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.search_btn")).click();
		Thread.sleep(1000);
		Assert.assertEquals(sale_id, driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_id")).getText());
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.view_btn")).click();
		Thread.sleep(1000);
		Assert.assertEquals(sale_id,
				driver.findElements(objectMap.getLocator("mis.ItemSalePage.isale_id")).get(2).getAttribute("value"));
		/*
		 * driver.navigate().back(); Thread.sleep(1000);
		 * driver.findElement(By.xpath(
		 * "//button[@class='btn btn-link search-reset']")).click();//返回所有搜索结果
		 * Thread.sleep(1000);
		 */
	}

	// 编辑按钮
	public WebElement edit_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSalePage.edit_btn"));
		return element;
	}
	
	public void isExist() throws Exception{
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		boolean b = driver.findElement(objectMap.getLocator("mis.ItemSalePage.immediately_btn")).isDisplayed();
		if (b) {

			driver.findElement(objectMap.getLocator("mis.ItemSalePage.immediately_btn")).click();

			driver.findElement(objectMap.getLocator("mis.ItemSalePage.sumit_btn")).click();
			Log.info("确认按钮已被点击");
		} else {
			Log.info("'立即生效'按钮不存在");
		}
	}

	// 编辑价格(正常价格)
	public void editSalePriceNormal() throws Exception {
		String supply_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(7)
				.getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).click();

		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).clear();
		// 生成随机的正常范围内的销售价格
		float max_price = (float) (supply_price * 1.2);
		float min_price = (float) (supply_price * 1.08);
		Random random = new Random();
		float sale_price = random.nextFloat() * (max_price - min_price) + min_price;
		// 保留两位小数
		DecimalFormat df = new DecimalFormat("#.00");
		String sale_price_normal = df.format(sale_price);
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).sendKeys(sale_price_normal);
		// 保存
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.save_btn")).click();
 
		alert_check("确认保存吗?");
		// 获取待生效价格
		String need_effect_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(10)
				.getAttribute("value");
		System.out.println(need_effect_price_s);
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		// 检测“立即生效”按钮是否存在

		 isExist();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// 保存成功
 
		 alert_check();
		// 获取新的销售价
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -550)");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//		String sale_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.newslae_price")).get(0)
//				.getAttribute("value");
//		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
//		// 比较新的销售价是否和带生效价格一致
//		Assert.assertEquals(need_effect_price, sale_price_new);
		
		
	}

	// 编辑价格(异常价格,确认更改)
	public void editSalePriceException() throws Exception {
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.edit_btn")).click();
		String supply_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(7)
				.getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).clear();
		// 输入一个较大的销售价
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).sendKeys("" + supply_price * 8);
		// 保存
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.save_btn")).click();
 
		alert_check("确认保存吗?");
		
		// 检测“确认更改”和“编辑”按钮是否存在
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

		Assert.assertTrue("'确认更改'按钮不存在",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.confrim_btn")).isDisplayed());
		Assert.assertTrue("'编辑'按钮不存在",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.Edit_btn")).isDisplayed());
		// 获取更改后价格
		DecimalFormat df = new DecimalFormat("#.00");
		String import_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(9)
				.getAttribute("value");
		String import_price = df.format(Float.parseFloat(import_price_s));
		// 点击“确认更改”
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.confrim_btn")).click();
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sumitPri_btn")).click();
		
//		Alert alert_success = driver.switchTo().alert();
//
//		Assert.assertEquals("保存成功", alert_success.getText());
//		alert.accept();
		alert_check();
		
		// 获取待生效价格
		String need_effect_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(10)
				.getAttribute("value");
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		// 检测待生效价格和更改后价格是否一致
		Assert.assertEquals(import_price, need_effect_price);
		// 检测“立即生效”按钮是否存在
		isExist();
		// 保存成功
//		Alert alert_success2 = driver.switchTo().alert();
//		Assert.assertEquals("保存成功", alert_success2.getText());
//		alert.accept();
		alert_check();
		
		// 获取新的销售价
		String sale_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(8)
				.getAttribute("value");
		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
		// 检测新的销售价是否和待生效价格一致
		Assert.assertEquals(need_effect_price, sale_price_new);
	}

	// 编辑价格(异常价格,编辑)
	public void editSalePriceExceptionEdit() throws Exception {
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.edit_btn")).click();
		String supply_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(7)
				.getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).clear();
		// 输入一个较大的销售价
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.sale_price1")).sendKeys("" + supply_price * 9);
		// 保存
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.save_btn")).click();
 
		alert_check("确认保存吗?");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		// 检测“确认更改”和“编辑”按钮是否存在
		Assert.assertTrue("'确认更改'按钮不存在",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.confrim_btn")).isDisplayed());
		Assert.assertTrue("'编辑'按钮不存在",
				driver.findElement(objectMap.getLocator("mis.ItemSalePage.Edit_btn")).isDisplayed());
		// 点击“编辑”
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.Edit_btn")).click();
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.editPrice_text")).clear();
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.editPrice_text")).sendKeys("8.88");
		driver.findElement(objectMap.getLocator("mis.ItemSalePage.editPrice_savebtn")).click();
 
		alert_check("确定保存销售价吗");
		
		// 获取待生效价格
		DecimalFormat df = new DecimalFormat("#.00");
		String need_effect_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(10)
				.getAttribute("value");
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		// 检测待生效价格和更改后价格是否一致
		Assert.assertEquals("8.88", need_effect_price);
		// 检测“立即生效”按钮是否存在
		isExist();
		// 保存成功
		
		alert_check();
		
		// 获取新的销售价
		String sale_price_s = driver.findElements(objectMap.getLocator("mis.ItemSalePage.sale_price")).get(8)
				.getAttribute("value");
		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
		// 检测新的销售价是否和待生效价格一致
		Assert.assertEquals(need_effect_price, sale_price_new);
	}

	// 取消按钮
	public WebElement cancel_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSalePage.cancel_btn"));
		return element;
	}

	// 保存按钮
	public WebElement save_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemSalePage.save_btn"));
		return element;
	}

	// 提示check
 
	public void alert_check(String str) {
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(str, alert.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
	}
	
	public void alert_check(){
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
	}
	
 
}