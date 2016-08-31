package com.mis.market.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Basic.Constant;
import Basic.properties;

import java.util.List;

public class MisHomegalleryPage {
	private WebElement element = null;
	private WebDriver driver = null;
	private properties objectMap = new properties(Constant.propertiesFilePath);// 配置文件相对路径
	
	public MisHomegalleryPage(WebDriver driver){
		this.driver = driver; 
	}
	
	//mis模块
	public WebElement mis() throws Exception{
		element = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.mis_btn")).get(6);
		Thread.sleep(1000);
		return element;
	}
		
	//品类推荐
	public WebElement homegallery() throws Exception{
		element = driver.findElement(objectMap.getLocator("mis.MisHomecatPage.Category_btn"));
		return element;
	}
		
	//添加按钮
	public WebElement insert_btn() throws Exception{
		element = driver.findElement(objectMap.getLocator("mis.MisHomegalleryPage.Add_btn"));
		return element;
	}
	
	//添加banner
	public void insert_banner(int m,int n,int banner_type,int activity_type,int sku_id,int activity_id,String url,String picture) throws Exception{
		List<WebElement> banner_list = driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.create_time"));
		//创建开始时间
		driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.create_time")).get(banner_list.size()-1).click();
		int begain_count = (banner_list.size()-1)*2+3;
		int end_count = (banner_list.size()-1)*2+4;
		driver.findElement(By.xpath("//html/body/div["+begain_count+"]/div[3]/table/tbody/tr[2]/td["+m+"]")).click();
		driver.findElement(By.xpath("//html/body/div["+begain_count+"]/div[2]/table/tbody/tr/td/span["+m+"]")).click();
		driver.findElement(By.xpath("//html/body/div["+begain_count+"]/div[1]/table/tbody/tr/td/span["+m+"]")).click();
		//创建结束时间
		driver.findElements(By.name("end_at")).get(banner_list.size()-1).click();
		driver.findElement(By.xpath("//html/body/div["+end_count+"]/div[3]/table/tbody/tr[5]/td["+n+"]")).click();
		driver.findElement(By.xpath("//html/body/div["+end_count+"]/div[2]/table/tbody/tr/td/span["+n+"]")).click();
		driver.findElement(By.xpath("//html/body/div["+end_count+"]/div[1]/table/tbody/tr/td/span["+n+"]")).click();
		//选择banner类型
		Select gallery_type = new Select(driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.banner_type")).get(banner_list.size()-1));
		gallery_type.selectByValue(""+banner_type); 
		//选择活动类型
		Select jump_typy = new Select(driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.jump_type")).get(banner_list.size()-1));
		jump_typy.selectByValue(""+activity_type);
		//根据活动类型填写不同的内容
		switch(activity_type){
		case 1:
			driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.activity_type")).get(banner_list.size()-1).sendKeys(""+sku_id);
			break;
		case 2:
			driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.activity_type")).get(banner_list.size()-1).sendKeys(""+activity_id);
			break;
		case 3:
			driver.findElements(objectMap.getLocator("mis.MisHomegalleryPage.activity_type")).get(banner_list.size()-1).sendKeys(url);
			break;
		case 4:
			//秒杀活动不用填写内容
			break;
		default:
			System.out.println("case存在问题");
			break;	
		}
		//上传图片
		WebElement upload =driver.findElements(objectMap.getLocator("mis.MisHomecatPage.fileUp_btn")).get(banner_list.size()-1);
		upload.sendKeys(picture);
		Thread.sleep(1000);
		//开关置为on
		List<WebElement> switch_list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.onoff_btn"));
		//等待元素加载出来
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(objectMap.getLocator("mis.MisHomecatPage.onoff_btn")));
		driver.findElements(objectMap.getLocator("mis.MisHomecatPage.onoff_btn")).get(switch_list.size()-1).click();
	}
	
	//保存并更新
	public WebElement save_btn() throws Exception{
		element = driver.findElement(objectMap.getLocator("mis.MisHomecatPage.save_btn"));
		return element;
	}
	
	//保存成功
	public void save_success(){
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("保存成功!", alert.getText());
		alert.accept();
	}
	
	//删除banner
	public WebElement delete_banner() throws Exception{
		List<WebElement> btn_list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.delete_btn"));
		element = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.delete_btn")).get(btn_list.size()-1);
		return element;
	}
}
