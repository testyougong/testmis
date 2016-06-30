package com.mis.market.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MisHomecatPage {
	private WebElement element = null;
	private WebDriver driver = null;
	
	public MisHomecatPage(WebDriver driver){
		this.driver = driver; 
	}
	
	//mis模块
	public WebElement mis() throws InterruptedException{
		element = driver.findElements(By.xpath("//div[@class='title']")).get(6);
		Thread.sleep(1000);
		return element;
	}
	
	//品类推荐
	public WebElement homecat(){
		element = driver.findElement(By.linkText("- 品类推荐"));
		return element;
	}
	
	//check品类推荐list
	public WebElement homecatCheck(String id){
		List<WebElement> list = driver.findElements(By.name("cat_id"));
		for(WebElement element:list){
			if(element.getAttribute("value").equals(id)){
				//System.out.println("存在名称为：" + name + " 的品类");
				return element;
			}
		}
		System.out.println("不存在名称为：" + id + " 的品类");
		return null;
	}
	
	//添加按钮
	public WebElement btn_insert(){
		element = driver.findElement(By.xpath("//button[@class='add btn btn-primary btn-lg']"));
		return element;
	}
	
	//添加品类推荐
	public void insertHomecat(String cat_name,String cat_id,String picture){
		List<WebElement> homecat_list = driver.findElements(By.name("cat_name"));
		//填写分类名称和分类id
		driver.findElements(By.name("cat_name")).get(homecat_list.size()-1).sendKeys(cat_name);
		driver.findElements(By.name("cat_id")).get(homecat_list.size()-1).sendKeys(cat_id);
		//上传图片
		WebElement upload =driver.findElements(By.name("fileUp")).get(homecat_list.size()-1);
		upload.sendKeys(picture);
		//开关置为on
		List<WebElement> switch_list = driver.findElements(By.xpath("//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']"));
		driver.findElements(By.xpath("//span[@class='bootstrap-switch-handle-off bootstrap-switch-default']")).get(switch_list.size()-1).click();	
	}
	
	//保存按钮
	public WebElement btn_save(){
		element = driver.findElement(By.xpath("//button[@class='submit btn btn-primary']"));
		return element;
	}
	
	//保存成功
	public void save_success(){
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("保存成功!", alert.getText());
		alert.accept();
	}
	
	//删除品类推荐
	public WebElement delete_cat(){
		List<WebElement> btn_list = driver.findElements(By.xpath("//button[@class='btn btn-primary actions']"));
		element = driver.findElements(By.xpath("//button[@class='btn btn-primary actions']")).get(btn_list.size()-1);
		return element;
	}
}
