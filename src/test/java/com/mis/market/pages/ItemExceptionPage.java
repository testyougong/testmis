package com.mis.market.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.BasicDriver;

public class ItemExceptionPage {
	private WebElement element = null;
	private WebDriver driver = null;

	public ItemExceptionPage(WebDriver driver){
		this.driver = driver;
	}
	
	//在售异常商品按钮
	public WebElement exception_btn(){
		element = driver.findElement(By.linkText("在售异常商品"));
		return element;
	}
	
	//商品状态筛选检查
	public void filterCheck(int n) throws InterruptedException{
		driver.findElement(By.xpath("//li[@data-value="+n+"]")).click();
		Thread.sleep(1000);
		String text = driver.findElement(By.xpath("//li[@data-value="+n+"]")).getText();
		try{
			if(BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li[1]/table/tbody/tr[1]/td[13]"))){
				String status = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li[1]/table/tbody/tr[1]/td[13]")).getText();
				if(text.contains("所有异常商品")){
					Assert.assertTrue("'所有'筛选错误",status.equals("A档异常商品") || status.equals("B档异常商品") || status.equals("C档异常商品") || status.equals("供货价为0商品") || status.equals("负毛利商品") || status.equals("供货价异常") || status.equals("销售价异常") || status.equals("促销价异常"));
				}else{
					Assert.assertTrue(text+" 筛选错误",text.contains(status));	
				}
			}else{
				String nullText = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li/table/tbody/tr/td")).getText();
				Assert.assertTrue("'所有'筛选错误",nullText.equals("暂无数据"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Thread.sleep(1000);
	}
	
	//物美状态筛选检查
	public void wmstatusCheck(int m) throws InterruptedException{
		driver.findElements(By.xpath("//select/option")).get(m).click();
		Thread.sleep(1000);
		String text = driver.findElements(By.xpath("//select/option")).get(m).getText();
		try{
			if(BasicDriver.isElementExist(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li[1]/table/tbody/tr[1]/td[14]"))){
				String wmstatus = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li[1]/table/tbody/tr[1]/td[14]")).getText();
				if(text.contains("所有物美状态")){
					Assert.assertTrue("'所有'筛选错误",wmstatus.equals("正常") || wmstatus.equals("正常(新品试销)") || wmstatus.equals("正常(新品评估)") || wmstatus.equals("异常") || wmstatus.equals("正常(目录团购)"));
				}else{
					Assert.assertTrue(text+" 筛选错误",text.contains(wmstatus));	
				}
			}else{
				String nullText = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/ul/li/table/tbody/tr/td")).getText();
				Assert.assertTrue("'所有'筛选错误",nullText.equals("暂无数据"));
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		Thread.sleep(1000);
	}
}
