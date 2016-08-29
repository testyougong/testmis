package Basic;

import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;

import Basic.properties;

public class BasicDriver /* extends properties */ {

	// public BasicDriver() throws IOException {
	// super();
	// }

	// public BasicDriver(String propFile) {
	// super(propFile);
	// // TODO Auto-generated constructor stub
	// }

	public static WebDriver driver;
	private static String propurl = "src//main//java//properties//market.properties";// 配置文件相对路径
	private static properties objectMap = new properties(propurl);

	// public static Navigation navigation;

	// 启动浏览器进入指定网址，最大化浏览器
	// public static void open() throws InterruptedException {
	// navigation.to(url);
	// driver.manage().window().maximize();
	// }
	//
	// // 登录
	// public static void login() throws InterruptedException {
	// driver.findElement(By.name("email")).sendKeys(email);
	// WebElement pw = driver.findElement(By.name("pwd"));
	// pw.sendKeys("" + password);
	// pw.submit();
	// Thread.sleep(1000);
	// }
	//
	// 判断元素是否存在
	public static boolean isElementExist(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// 判断元素是否存在
	public static boolean isExist(String str) throws Exception {

		if (driver.findElement(objectMap.getLocator(str)) != null) {
			return true;
		} else {
			return false;
		}

	}

	// 屏幕截图
	public static void takeScreenshots(String path) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 窗口切换
	public static void windows(int i) {
		String[] handles = new String[driver.getWindowHandles().size()];
		driver.getWindowHandles().toArray(handles);
		driver.switchTo().window(handles[i]);
	}

	// 时间方法
	public static String time() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	// 获得某元素的文本描述信息
	public static String getElementText(By by) {
		try {
			return driver.findElement(by).getText();
		} catch (NoSuchElementException e) {
			return "Text does not exist!";
		}
	}

	/*
	 *** 某些元素由于属于某个iframe的需要先定义到frame然后再在此frame里边查找该元素 此方法适合定位文本链接型元素
	 */
	public static void frame(By by, String text) {
		WebElement element = driver.switchTo().frame(text).findElement(by);
		element.click();
	}

	/*
	 *** 某些元素由于属于某个iframe的需要先定义到frame然后再在此frame里边查找该元素 此方法适合定位文本输入框型元素
	 */
	public static void frameElementSendKey(By by, String text) {
		WebElement element = driver.switchTo().frame(text).findElement(by);
		element.sendKeys(text);
	}

	// 控制滚动条方法
	public static void setScroll(WebDriver driver, int height) {
		try {
			String setscroll = "document.documentElement.scrollTop=" + height;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(setscroll);
		} catch (Exception e) {
			System.out.println("Fail to set the scroll.");
		}

		// 通过ID/name定位滚动条，并且滑动

		((JavascriptExecutor) driver).executeScript("document.getElementById('menuContent').scrollTop = 441");
		// 移动到指定的坐标(相对当前的坐标移动)
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700)");

	}
}
