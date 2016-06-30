package com.mis.market.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemCategoryPage {
	private WebElement element = null;
	private WebDriver driver = null;

	public ItemCategoryPage(WebDriver driver){
		this.driver = driver;
	}
	
	//品类管理入口
	public WebElement category_btn(){
		element = driver.findElement(By.linkText("品类管理"));
		return element;
	}	
	
	//check筛选
	public void filterCheck(int n) throws InterruptedException{
		driver.findElement(By.xpath("//li[@data-value="+n+"]")).click();
		List<WebElement> list= driver.findElements(By.linkText("查看"));
		//验证筛选出来的数量和实际数量是否一致
		String text = driver.findElement(By.xpath("//li[@data-value="+n+"]")).getText();
		StringBuffer strb = new StringBuffer(text);
		String sta_count = text.substring(3,strb.length()-1);
		int sta_counts = Integer.parseInt(sta_count);
		Assert.assertEquals(sta_counts, list.size());
		//验证筛选的状态是否正确
		ArrayList<String> status_list = new ArrayList<String>();
		for(int m=1;m<=list.size();m++){
			String status = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/table/tbody/tr["+m+"]/td[6]")).getText();
			status_list.add(status);
		}
		if(n == 2){
			for(String sta:status_list){
				if(sta.equals("上架")){
					System.out.println("'下架'筛选错误");
				}
			}
		}
		else if(n == 0){
			for(String sta:status_list){
				if(sta.equals("下架")){	
					System.out.println("'上架'筛选错误");
				}
			}
		}
		else if(n == -1){
			for(String sta:status_list){
				if(sta.equals("上架") || sta.equals("下架")){	
				}else{
					System.out.println("'全部'筛选错误");
				}
			}
		}			
	}
	
	//添加分类
	public WebElement insert_btn(){
		element = driver.findElement(By.linkText("添加分类"));
		return element;
	}
	
	//取消按钮
	public WebElement cancel_btn(){
		element = driver.findElement(By.xpath("//button[@data-trigger='cancel']"));
		return element;
	}
	
	//保存按钮
	public WebElement save_btn(){
		element = driver.findElement(By.xpath("//button[@data-trigger='save']"));
		return element;
	}
	
	//查看
	public WebElement view_btn(){
		element = driver.findElement(By.linkText("查看"));
		return element;
	}
	
	//保存按钮
	public WebElement edit_btn(){
		element = driver.findElement(By.linkText("编辑"));
		return element;
	}
	
}
