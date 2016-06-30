package com.mis.market.pages;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.BasicDriver;


public class ItemSalePage{
	private WebElement element = null;
	private WebDriver driver = null;
		
	public ItemSalePage(WebDriver driver){
        this.driver = driver;
	}
		
	//销售管理按钮
	public WebElement sale_btn() throws InterruptedException{
		element = driver.findElement(By.linkText("销售管理"));
		Thread.sleep(1000);
		return element;
	}
		
	//筛选
	public WebElement filter(int n) throws InterruptedException{
		element = driver.findElement(By.xpath("//li[@data-value="+n+"]"));
		Thread.sleep(1000);
		return element;
	}
		
	//筛选文案检查
	public void filterCheck(){
		Assert.assertTrue("'所有'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='0']")).getText().contains("所有"));
		Assert.assertTrue("'新建'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='1']")).getText().contains("新建"));	
		Assert.assertTrue("'下架'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='3']")).getText().contains("下架"));		
		Assert.assertTrue("'上架'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='2']")).getText().contains("上架"));			
		Assert.assertTrue("'待生效价格'筛选文案错误", driver.findElement(By.xpath("//li[@data-value='4']")).getText().contains("待生效价格"));
	}
		
	//销售列表数量检查
	public void listCheck(){
		List<WebElement> sale_list= driver.findElements(By.linkText("查看"));
		Assert.assertEquals(12,sale_list.size());
	}
		
	//搜索检查
	public void saleSearchCheck(String sale_id) throws InterruptedException{
		driver.findElement(By.xpath("//input[@class='field-control search-item']")).sendKeys(sale_id);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(sale_id,driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div[1]/table/tbody/tr/td[3]")).getText());
		driver.findElement(By.linkText("查看")).click();
		Thread.sleep(1000);
		Assert.assertEquals(sale_id,driver.findElements(By.xpath("//input[@class='field-control']")).get(2).getAttribute("value"));
		/*
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-link search-reset']")).click();//返回所有搜索结果
		Thread.sleep(1000);
		*/
	}
		
	//编辑按钮
	public WebElement edit_btn(){
		element = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-edit']"));
		return element;
	}
	
	//编辑价格(正常价格)
	public void editSalePriceNormal(){
		String supply_price_s = driver.findElements(By.className("field-control")).get(7).getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(By.name("sale_price")).clear();
		//生成随机的正常范围内的销售价格
		float max_price = (float) (supply_price*1.2);
		float min_price = (float) (supply_price*1.08);
		Random random = new Random();
		float sale_price = random.nextFloat()*(max_price-min_price) + min_price;
		//保留两位小数
		DecimalFormat df = new DecimalFormat("#.00");
		String sale_price_normal = df.format(sale_price);
		driver.findElement(By.name("sale_price")).sendKeys(sale_price_normal);
		//保存
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-save']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("确认保存吗?", alert.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
		//获取待生效价格
		String need_effect_price_s = driver.findElements(By.className("field-control")).get(10).getAttribute("value");
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		//检测“立即生效”按钮是否存在
		if(BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button"))){
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button")).click();
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div/div[2]/button[1]")).click();
		}else{
			System.out.println("'立即生效'按钮不存在");
			}
		//保存成功
		Alert alert_success = driver.switchTo().alert();
		Assert.assertEquals("保存成功", alert_success.getText());
		alert.accept();
		//获取新的销售价
		String sale_price_s = driver.findElements(By.className("field-control")).get(8).getAttribute("value");
		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
		//比较新的销售价是否和带生效价格一致
		Assert.assertEquals(need_effect_price, sale_price_new);
	}
	
	//编辑价格(异常价格,确认更改)
	public void editSalePriceException(){
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-edit']")).click();
		String supply_price_s = driver.findElements(By.className("field-control")).get(7).getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(By.name("sale_price")).clear();
		//输入一个较大的销售价
		driver.findElement(By.name("sale_price")).sendKeys("" + supply_price*8);
		//保存
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-save']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("确认保存吗?", alert.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
		//检测“确认更改”和“编辑”按钮是否存在
		Assert.assertTrue("'确认更改'按钮不存在", BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[1]")));
		Assert.assertTrue("'编辑'按钮不存在", BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[2]")));
		//获取更改后价格
		DecimalFormat df = new DecimalFormat("#.00");
		String import_price_s = driver.findElements(By.className("field-control")).get(9).getAttribute("value");	
		String import_price = df.format(Float.parseFloat(import_price_s));
		//点击“确认更改”
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[1]")).click();
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/div/div/div[2]/button[1]")).click();
		Alert alert_success = driver.switchTo().alert();
		Assert.assertEquals("保存成功", alert_success.getText());
		alert.accept();
		//获取待生效价格
		String need_effect_price_s = driver.findElements(By.className("field-control")).get(10).getAttribute("value");
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		//检测待生效价格和更改后价格是否一致
		Assert.assertEquals(import_price,need_effect_price);
		//检测“立即生效”按钮是否存在
		if(BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button"))){
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button")).click();
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div/div[2]/button[1]")).click();
		}else{
			System.out.println("'立即生效'按钮不存在");
			}
		//保存成功
		Alert alert_success2 = driver.switchTo().alert();
		Assert.assertEquals("保存成功", alert_success2.getText());
		alert.accept();
		//获取新的销售价
		String sale_price_s = driver.findElements(By.className("field-control")).get(8).getAttribute("value");
		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
		//检测新的销售价是否和待生效价格一致
		Assert.assertEquals(need_effect_price, sale_price_new);		
	}
	
	//编辑价格(异常价格,编辑)
	public void editSalePriceExceptionEdit(){
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-edit']")).click();
		String supply_price_s = driver.findElements(By.className("field-control")).get(7).getAttribute("value");
		float supply_price = Float.parseFloat(supply_price_s);
		driver.findElement(By.name("sale_price")).clear();
		//输入一个较大的销售价
		driver.findElement(By.name("sale_price")).sendKeys("" + supply_price*9);
		//保存
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-save']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("确认保存吗?", alert.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
		//检测“确认更改”和“编辑”按钮是否存在
		Assert.assertTrue("'确认更改'按钮不存在", BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[1]")));
		Assert.assertTrue("'编辑'按钮不存在", BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[2]")));
		//点击“编辑”
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[10]/div/button[2]")).click();
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[9]/input")).clear();
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[9]/input")).sendKeys("8.88");
		driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[9]/button[2]")).click();
		Alert alert_edit = driver.switchTo().alert();
		Assert.assertEquals("确定保存销售价吗", alert_edit.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert_edit.getText());
		alert.accept();
		//获取待生效价格
		DecimalFormat df = new DecimalFormat("#.00");
		String need_effect_price_s = driver.findElements(By.className("field-control")).get(10).getAttribute("value");
		String need_effect_price = df.format(Float.parseFloat(need_effect_price_s));
		//检测待生效价格和更改后价格是否一致
		Assert.assertEquals("8.88",need_effect_price);
		//检测“立即生效”按钮是否存在
		if(BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button"))){
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/div/div[11]/button")).click();
			driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[5]/div/div/div[2]/button[1]")).click();
		}else{
			System.out.println("'立即生效'按钮不存在");
			}
		//保存成功
		Alert alert_success2 = driver.switchTo().alert();
		Assert.assertEquals("保存成功", alert_success2.getText());
		alert.accept();
		//获取新的销售价
		String sale_price_s = driver.findElements(By.className("field-control")).get(8).getAttribute("value");
		String sale_price_new = df.format(Float.parseFloat(sale_price_s));
		//检测新的销售价是否和待生效价格一致
		Assert.assertEquals(need_effect_price, sale_price_new);		
	}
		
	//取消按钮
	public WebElement cancel_btn(){
		element = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-cancel']"));
		return element;
	}
		
	//保存按钮
	public WebElement save_btn(){
		element = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-save']"));
		return element;
	}
		
	//提示check
	public void alert_check(){
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("确认保存吗?", alert.getText());
		alert.accept();
		Assert.assertEquals("保存成功", alert.getText());
		alert.accept();
	}	
}