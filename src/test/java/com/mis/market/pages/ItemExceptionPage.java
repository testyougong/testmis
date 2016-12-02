package com.mis.market.pages;

import java.util.ArrayList;
import java.util.List;
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
	//在售异常商品页面
	private WebElement element = null;
	//private String propurl ="src//main//java//properties//market.properties";// 配置文件相对路径
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

			Scroll();//能够滑动，说明存在数据（因为有状态）
			
			
//
//			if (BasicDriver.isExist("mis.ItemExceptionPage.state_btn")) {
//				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//				String status = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
//				if (text.contains("所有异常商品")) {
//					try {
//						if (BasicDriver.isElementExist(objectMap.getLocator("mis.ItemExceptionPage.state_btn"))) {
//							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//							driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
//							if (text.contains("所有异常商品")) {
//								Assert.assertTrue("'所有'筛选错误", status.equals("A档异常商品") || status.equals("B档异常商品")
//										|| status.equals("C档异常商品") || status.equals("供货价为0商品") || status.equals("负毛利商品")
//										|| status.equals("供货价异常") || status.equals("销售价异常") || status.equals("促销价异常"));
//							} else {
//								Assert.assertTrue(text + " 筛选错误", text.contains(status));
//							}
//						} else {
//							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//							String nullText = driver
//									.findElement(objectMap.getLocator("mis.ItemExceptionPage.message_text")).getText();
//
//							Assert.assertTrue("'所有'筛选错误", nullText.equals("暂无数据"));
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//					Thread.sleep(1000);
//				}
//				// 还有别的tab判断
//			}
		}

	}
	
	
	public void isEquals(String str) throws Exception{
		
		//思路:1.首先进入每个状态页  再获取各状态的的数据进行判断 
		 
		//根据str 进行状态页面 
		driver.findElement(By.xpath("//li[contains(text(),'"+str+"')]")).click();//这个可以在test脚本中进入
		Scroll();
		//根据查看按钮获取所有的数量
		//问题 ：有分页，获取到的只是当前页面的数据，不能进行所有数据的判断，这是有问题的。。。
		List<WebElement> list = driver.findElements(objectMap.getLocator("mis.ItemExceptionPage.view_btn"));
		
        //通过循环遍历将所有数据对应的状态存储到一个列表中
		ArrayList<String> status_list = new ArrayList<String>();
		
		for(int i=1;i<=list.size();i++){
			String status = driver 
					.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/div/div[1]/table/tbody/tr["+i+"]/td[14]"))
					.getText();
			status_list.add(status);
		}
		
		//根据str和status_list 中的值进行判断是否正确
		//思路，通过遍历status_list ，如果里面含有非str状态的状态，则认为状态筛选不正确，可否ok？？
		
		
		for (String sta:status_list){
			if (str!=sta){
				Assert.fail();
				Log.info("状态筛选错误");
			}else{
				Log.info("状态筛选ok");
			}
		}
		
		
		
		
		/*
		
		//判断各状态是否正确的方法
		if (BasicDriver.isExist("mis.ItemExceptionPage.state_btn")) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//这儿有问题。。。。只获取到一条数据？？ 无法保证数据是否正确吧？？ 应该获取所有的数据 再进行判断，还没想到好的方法
			//String status = driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
			
			
			
			//List<WebElement> status=driver.findElements(By.xpath("//html/body/div[1]/div[2]/div/div/div[4]/div/div[1]/table/tbody/tr["+num+"]/td[14]"));
			
			
			 
			if (str.contains("所有异常商品")) {
				try {
					if (BasicDriver.isElementExist(objectMap.getLocator("mis.ItemExceptionPage.state_btn"))) {
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						driver.findElement(objectMap.getLocator("mis.ItemExceptionPage.state_btn")).getText();
						if (str.contains("所有异常商品")) {
							Assert.assertTrue("'所有'筛选错误", status.equals("A档异常商品") || status.equals("B档异常商品")
									|| status.equals("C档异常商品") || status.equals("供货价为0商品") || status.equals("负毛利商品")
									|| status.equals("供货价异常") || status.equals("销售价异常") || status.equals("促销价异常"));
						} else {
							//Assert.assertTrue(str + " 筛选错误", str.contains(status));
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
		
		*/
		
	}

	public void wmstatusCheck1(int m) throws Exception {
		//物美状态筛选

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
