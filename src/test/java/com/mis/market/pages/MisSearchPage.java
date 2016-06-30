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

import static org.testng.Assert.assertEquals;

public class MisSearchPage {
    private WebElement element = null;
    private WebDriver driver = null;

    public MisSearchPage(WebDriver driver){
        this.driver = driver;
    }

    //mis模块
    public WebElement mis() throws InterruptedException{
        element = driver.findElements(By.xpath("//div[@class='title']")).get(6);
        Thread.sleep(1000);
        return element;
    }

    //分类商品推荐
    public WebElement search(){
        element = driver.findElement(By.linkText("搜索"));
        return element;
    }

    //添加按钮
    public WebElement insertHotq_btn(){
        element = driver.findElement(By.xpath("//button[@class='btn btn-primary add']"));
        return element;
    }

    //填写热搜词
    public void insertHotq(String text){
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(text);
        driver.findElement(By.xpath("//button[@class='btn btn-primary save-update']")).click();
        driver.switchTo().alert().accept();
    }

    //检查热搜词是否添加成功
    public void checkHotqInsert(String text){
        List<WebElement> list = driver.findElements(By.xpath("//button[@class= 'btn btn-link delete']"));
        int position = list.size();
        assertEquals (text,driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/table/tbody/tr["+position+"]/td[2]")).getText());
    }

    //删除热搜词
    public void deleteHotq(){
        List<WebElement> list = driver.findElements(By.xpath("//button[@class= 'btn btn-link delete']"));
        int position = list.size()-1;
        driver.findElements(By.xpath("//button[@class= 'btn btn-link delete']")).get(position).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//button[@class='btn btn-primary save-update']")).click();
        driver.switchTo().alert().accept();
    }

    //检查热搜词是否删除成功
    public void checkHotqDelete(String text){
        List<WebElement> list = driver.findElements(By.xpath("//button[@class= 'btn btn-link delete']"));
        int position = list.size();
        if((driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/table/tbody/tr["+position+"]/td[2]")).getText().equals(text))){
            System.out.print("热搜词没有删除成功");
            assertEquals (text,driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/div/div[3]/table/tbody/tr["+position+"]/td[2]")).getText());
        }

    }


}