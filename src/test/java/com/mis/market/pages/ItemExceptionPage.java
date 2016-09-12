package com.mis.market.pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Basic.BasicDriver;
import Basic.Constant;
import Basic.Log;
import Basic.properties;

public class ItemExceptionPage {
	private WebElement element = null;
	//private String propurl = "src//main//java//properties//market.properties";// 配置文件相对路径
	private properties objectMap = new properties(Constant.propertiesFilePath);
	private WebDriver driver = null;

	public ItemExceptionPage(WebDriver driver) {
		this.driver = driver;
	}

	// 在售异常商品按钮
	public WebElement exception_btn() throws Exception {
		element = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.exception_btn"));
		return element;
	}

	public void Scroll() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement webElement = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_data1"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
		WebElement webElement1 = driver.findElement(objectMap.getLocator("mis.ItmeExceptionPage.total_btn"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement1);
		Thread.sleep(3000);
	}

	// 商品状态筛选检查

	public void filterCheck(int n) throws Exception {
		driver.findElement(By.xpath("//li[@data-value=" + n + "]")).click();

		Thread.sleep(1000);
		String text = driver.findElement(By.xpath("//li[@data-value=" + n + "]")).getText();
		if (text.contains("(0)")) {
			Log.info("暂无数据");

		} else {
			// 判断是否存在数据，如果存在进行判断
			Thread.sleep(1000);

			Scroll();// 能够滑动，说明存在数据（因为有状态）

			if (BasicDriver.isExist("mis.ItemExceptionPage.state_btn")) {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String status = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
				if (text.contains("所有异常商品")) {
					try {
						if (BasicDriver.isElementExist(objectMap.getLocator("mis.ItemExceptionPage.state_btn"))) {
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
							if (text.contains("所有异常商品")) {
								Assert.assertTrue("'所有'筛选错误", status.equals("A档异常商品") || status.equals("B档异常商品")
										|| status.equals("C档异常商品") || status.equals("供货价为0商品") || status.equals("负毛利商品")
										|| status.equals("供货价异常") || status.equals("销售价异常") || status.equals("促销价异常"));
							} else {
								Assert.assertTrue(text + " 筛选错误", text.contains(status));
							}
						} else {
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							String nullText = driver
									.findElement(objectMap.getLocator("mis.ItemExceptionPage.message_text")).getText();

							Assert.assertTrue("'所有'筛选错误", nullText.equals("暂无数据"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					Thread.sleep(1000);
				}
				// 还有别的tab判断
			}
		}

	}

	public void wmstatusCheck1(int m) throws Exception {

		driver.findElements(objectMap.getLocator("mis.ItemExceptionPage.select_btn")).get(m).click();
		Thread.sleep(1000);
		String text = driver.findElements(objectMap.getLocator("mis.ItemExceptionPage.select_btn")).get(m).getText();
		try {

			Scroll();

			if (BasicDriver.isExist("mis.ItemExceptionPage.wum_state")) {
				String wmstatus = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.wum_state")).getText();
				if (text.contains("所有物美状态")) {
					Assert.assertTrue("'所有'筛选错误", wmstatus.equals("正常") || wmstatus.equals("正常(新品试销)")
							|| wmstatus.equals("正常(新品评估)") || wmstatus.equals("异常") || wmstatus.equals("正常(目录团购)"));
				} else {
					Assert.assertTrue(text + " 筛选错误", text.contains(wmstatus));
				}
			} else {

				String nullText = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_data"))
						.getText();
				if (nullText.equals(("暂无数据"))) {

					Log.info("所选选项暂无数据");
				}
				// else{
				// Assert.assertTrue("'所有'筛选错误", nullText.equals("暂无数据"));
				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
	}
}
