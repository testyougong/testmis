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

public class MisRecomcatPage {
    private WebElement element = null;
    private WebDriver driver = null;

    public MisRecomcatPage(WebDriver driver){
        this.driver = driver;
    }

    //mis模块
    public WebElement mis() throws InterruptedException{
        element = driver.findElements(By.xpath("//div[@class='title']")).get(6);
        Thread.sleep(1000);
        return element;
    }

    //分类商品推荐
    public WebElement recomcat(){
        element = driver.findElement(By.linkText("- 分类商品推荐"));
        return element;
    }

    //添加按钮
    public WebElement insertCat_btn(){
        element = driver.findElement(By.xpath("//button[@class='btn btn-primary add']"));
        return element;
    }

    //填写分类id
    public String insertCat(String cat_id , String cat_name){
        driver.findElements(By.xpath("//input[@type='text']")).get(0).sendKeys(""+cat_id);
        driver.findElements(By.xpath("//input[@type='text']")).get(1).sendKeys(""+cat_name);
        return cat_id;
    }

    //查看详情按钮
    public WebElement view() throws IOException {
        List<WebElement> list = driver.findElements(By.linkText("查看详情"));
        int position = list.size()-1;
        element = driver.findElements(By.linkText("查看详情")).get(position);
        return element;
    }

    //保存并更新按钮
    public WebElement save_btn(){
        element = driver.findElement(By.xpath("//button[@class='btn btn-primary save-update']"));
        return element;
    }

    //二次确认按钮
    public void accept_btn(){
        driver.switchTo().alert().accept();
    }

    //检查添加是否成功
    public void checkInsert(String cat_id , String cat_name) throws IOException {
        List<WebElement> list = driver.findElements(By.linkText("查看详情"));
        int position = list.size()-1;
        Assert.assertEquals(cat_id,driver.findElements(By.xpath("//td[@class='id']")).get(position).getText());
        Assert.assertEquals(cat_name,driver.findElements(By.xpath("//td[@class='cat-name']")).get(position).getText());
    }

    //添加商品按钮
    public WebElement insertItem_btn() throws IOException, InterruptedException {
        BasicDriver bd = new BasicDriver();
        bd.windows(1);
        element = driver.findElement(By.xpath("//button[@class='btn btn-primary add']"));
        return element;
    }

    //添加商品
    public void insertItem() throws IOException {
        BasicDriver bd = new BasicDriver();
        bd.windows(1);
        driver.findElements(By.xpath("//input[@type='checkbox']")).get(0).click();
        String Item_name = driver.findElement(By.xpath("//td[@class='wrap']")).getText();
        driver.findElement(By.xpath("//button[@class='btn btn-primary save']")).click();
        driver.switchTo().alert().accept();
        List<WebElement> list = driver.findElements(By.className("name"));
        int position = list.size()-1;
        Assert.assertEquals(Item_name,driver.findElements(By.className("name")).get(position).getText());
    }

    //删除分类
    public void deleteCat() throws IOException {
        BasicDriver bd = new BasicDriver();
        bd.windows(0);
        List<WebElement> list = driver.findElements(By.linkText("查看详情"));
        int position = list.size()-1;
        driver.findElements(By.xpath("//button[@class='btn btn-link delete']")).get(position).click();
        driver.switchTo().alert().accept();
    }

    //检查删除是否成功
    public void checkDelete(String cat_id , String cat_name) throws IOException {
        List<WebElement> list = driver.findElements(By.linkText("查看详情"));
        int position = list.size()-1;
        if(driver.findElements(By.xpath("//td[@class='id']")).get(position).getText().equals(cat_id) || driver.findElements(By.xpath("//td[@class='cat-name']")).get(position).getText().equals(cat_name)) {
            System.out.println("删除没有生效");
        }
    }

}
