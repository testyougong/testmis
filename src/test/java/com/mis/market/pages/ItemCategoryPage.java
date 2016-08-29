package com.mis.market.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.Constant;
import Basic.properties;

public class ItemCategoryPage {
	private WebElement element = null;
	//private String propurl = "src//main//java//properties//market.properties";// 配置文件相对路径
	private properties objectMap = new properties(Constant.propertiesFilePath);
	private WebDriver driver = null;

	public ItemCategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	// //品类管理入口
	public WebElement category_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.category_btn"));
		return element;
	}
	//
	// //check筛选
	// public void filterCheck(int n) throws Exception{
	// driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.shelves_btn")).click();
	// //By.linkText("查看")
	// List<WebElement> list=
	// driver.findElements(objectMap.getLocator("mis.ItemCategoryPage.view_btn"));
	// //验证筛选出来的数量和实际数量是否一致 By.xpath("//li[@data-value="+n+"]")
	// String text =
	// driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.shelves_btn")).getText();
	// StringBuffer strb = new StringBuffer(text);
	// String sta_count = text.substring(3,strb.length()-1);
	// int sta_counts = Integer.parseInt(sta_count);
	// Assert.assertEquals(sta_counts, list.size());
	// //验证筛选的状态是否正确
	// ArrayList<String> status_list = new ArrayList<String>();
	// for(int m=1;m<=list.size();m++){
	// String status =
	// driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/table/tbody/tr["+m+"]/td[6]")).getText();
	// status_list.add(status);
	// }
	// if(n == 2){
	// for(String sta:status_list){
	// if(sta.equals("上架")){
	// System.out.println("'下架'筛选错误");
	// }
	// }
	// }
	// else if(n == 0){
	// for(String sta:status_list){
	// if(sta.equals("下架")){
	// System.out.println("'上架'筛选错误");
	// }
	// }
	// }
	// else if(n == -1){
	// for(String sta:status_list){
	// if(sta.equals("上架") || sta.equals("下架")){
	// }else{
	// System.out.println("'全部'筛选错误");
	// }
	// }
	// }
	// }
	//
	//
	//
	//

	// //check筛选
	public void filterCheck(String str) throws Exception {
		String text = null;
		if (str == "上架") {

			driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.shelves_btn")).click();
			text = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.shelves_btn")).getText();

			// By.linkText("查看")
		} else if (str == "下架") {
			driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.Theshelves_btn")).click();
			text = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.Theshelves_btn")).getText();
		} else {
			driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.total_btn")).click();
			text = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.total_btn")).getText();
		}

		// 验证筛选出来的数量和实际数量是否一致 By.xpath("//li[@data-value="+n+"]")
		List<WebElement> list = driver.findElements(objectMap.getLocator("mis.ItemCategoryPage.view_btn"));

		StringBuffer strb = new StringBuffer(text);
		String sta_count = text.substring(3, strb.length() - 1);
		int sta_counts = Integer.parseInt(sta_count);
		Assert.assertEquals(sta_counts, list.size());
		// 验证筛选的状态是否正确
		ArrayList<String> status_list = new ArrayList<String>();
		for (int m = 1; m <= list.size(); m++) {
			String status = driver
					.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/table/tbody/tr[" + m + "]/td[7]"))
					.getText();
			status_list.add(status);
		}
		if (str == "下架") {
			for (String sta : status_list) {
				if (sta.equals("上架")) {
					System.out.println("'下架'筛选错误");
				}
			}
		} else if (str == "上架") {
			for (String sta : status_list) {
				if (sta.equals("下架")) {
					System.out.println("'上架'筛选错误");
				}
			}
		} else if (str == "所有") {
			for (String sta : status_list) {

				if (sta.equals("上架") || sta.equals("下架")) {

				} else {
					System.out.println("'全部'筛选错误");
				}
			}
		}
	}

	// 添加分类
	public WebElement insert_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.insert_btn"));
		return element;
	}

	// 取消按钮
	public WebElement cancel_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.cancel_btn"));
		return element;
	}

	// 保存按钮
	public WebElement save_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.save_btn"));
		return element;
	}

	// 查看
	public WebElement view_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.view_btn"));
		return element;
	}

	// 编辑按钮
	public WebElement edit_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemCategoryPage.edit_btn"));
		return element;
	}

}
