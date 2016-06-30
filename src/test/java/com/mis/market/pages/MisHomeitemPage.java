package com.mis.market.pages;

import Basic.BasicDriver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MisHomeitemPage {
    private WebElement element = null;
    private WebDriver driver = null;

    public MisHomeitemPage(WebDriver driver){
		this.driver = driver;
	}
	
	//mis模块
	public WebElement mis() throws InterruptedException{
		element = driver.findElements(By.xpath("//div[@class='title']")).get(6);
		Thread.sleep(1000);
		return element;
	}
			
	//品类推荐
	public WebElement homeitem(){
		element = driver.findElement(By.linkText("- 单品推荐"));
		return element;
	}

	//添加按钮
	public WebElement insert_btn(){
		element = driver.findElement(By.xpath("//a[@class='btn btn-primary btn-lg']"));
		return element;
	}

	//添加商品
    public  void insert_item() throws IOException, InterruptedException {
        BasicDriver bd = new BasicDriver();
        bd.windows(1);//切换窗口
        driver.findElement(By.xpath("//li[@data-value='1']")).click();
        Thread.sleep(500);
        String name = driver.findElement(By.xpath("//tr[@class=' tpl-filter-promo-invalid']/td[2]")).getText();
        driver.findElement(By.xpath("//tr[@class=' tpl-filter-promo-invalid']/td[7]/a[@class='act-additem']")).click();
        driver.switchTo().alert().accept();//现在是取消,回头改成确定
        bd.windows(0);//返回原窗口
        driver.navigate().refresh();//刷新页面
        List<WebElement> list = driver.findElements(By.linkText("查看"));
        int position = list.size();
        Assert.assertEquals(name,driver.findElement(By.xpath("//tr["+position+"]/td[2]/span")).getText());
    }

}