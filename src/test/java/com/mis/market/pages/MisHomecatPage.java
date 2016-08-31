package com.mis.market.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.Constant;
import Basic.properties;

public class MisHomecatPage {
	private WebElement element = null;
	private WebDriver driver = null;
	private properties objectMap = new properties(Constant.propertiesFilePath);// 配置文件相对路径

	public MisHomecatPage(WebDriver driver) {
		this.driver = driver;
	}

	// mis模块
	public WebElement mis() throws Exception {
		element = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.mis_btn")).get(6);
		Thread.sleep(1000);
		return element;
	}

	// 品类推荐
	public WebElement homecat() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.MisHomecatPage.Category_btn"));
		return element;
	}

	// check品类推荐list
	public WebElement homecatCheck(String id) throws Exception {
		List<WebElement> list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.cat_id"));
		for (WebElement element : list) {
			if (element.getAttribute("value").equals(id)) {
				// System.out.println("存在名称为：" + name + " 的品类");
				return element;
			}
		}
		System.out.println("不存在名称为：" + id + " 的品类");
		return null;
	}

	// 添加按钮
	public WebElement btn_insert() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.MisHomecatPage.add_btn"));
		return element;
	}

	// 添加品类推荐
	public void insertHomecat(String cat_name, String cat_id, String picture) throws Exception {
		List<WebElement> homecat_list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.cat_name"));
		// 填写分类名称和分类id
		driver.findElements(objectMap.getLocator("mis.MisHomecatPage.cat_name")).get(homecat_list.size() - 1)
				.sendKeys(cat_name);
		driver.findElements(objectMap.getLocator("mis.MisHomecatPage.cat_id")).get(homecat_list.size() - 1)
				.sendKeys(cat_id);
		// 上传图片
		WebElement upload = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.fileUp_btn"))
				.get(homecat_list.size() - 1);
		//upload.click();
		 
		upload.sendKeys(picture);
		Thread.sleep(3000);
		// 开关置为on
		List<WebElement> switch_list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.onoff_btn"));
		driver.findElements(objectMap.getLocator("mis.MisHomecatPage.onoff_btn")).get(switch_list.size() - 1).click();
	}

	// 保存按钮
	public WebElement btn_save() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.MisHomecatPage.save_btn"));
		return element;
	}

	// 保存成功
	public void save_success() {
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("保存成功!", alert.getText());
		alert.accept();
	}

	// 删除品类推荐
	public WebElement delete_cat() throws Exception {
		List<WebElement> btn_list = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.delete_btn"));
		element = driver.findElements(objectMap.getLocator("mis.MisHomecatPage.delete_btn")).get(btn_list.size() - 1);
		return element;
	}
}
