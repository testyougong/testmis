package Basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class properties {
	
	/*protected static String url;
	protected static String email;	
	protected static String password;
	
	public properties() throws IOException{
		Properties pro = new Properties();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("D://GitProsty//testmis//src//main//java//properties//market.properties")); 
		pro.load(in);
		
		properties.url = pro.getProperty("url");
		properties.email = pro.getProperty("email");
		properties.password = pro.getProperty("password");		
	}*/
	
	
	Properties properties;

	public properties(String propFile) {
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("读取对象文件出错！");
			e.printStackTrace();
		}

	}
	public String getValue(String ElementNameInpropFile) throws Exception{
		
		// 根据变量ElementNameInpropFile，从属性配置文件中读取对应的配置对象
				String locator = properties.getProperty(ElementNameInpropFile);
				// 将配置对象中的定位类型存到locatorType变量，将定位表达式的值存入locatorValue变量
				String locatorValue = locator.split(">")[1];
				locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
				return locatorValue;
		
	}

	public By getLocator(String ElementNameInpropFile) throws Exception {
		// 根据变量ElementNameInpropFile，从属性配置文件中读取对应的配置对象
		String locator = properties.getProperty(ElementNameInpropFile);
		// 将配置对象中的定位类型存到locatorType变量，将定位表达式的值存入locatorValue变量
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		/*
		 * Eclipse 中的配置文件默认为ISO-8859-1编码存储，使用getBytes方法可以将字符串编码转换成为utf-8编码，
		 * 以此来解决在配置文件读取中文乱码的问题
		 */
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		// 输出locatorType变量值和locatorValue变量值，验证是否赋值正确
		System.out.println("获取的定位类型:" + locatorType + "  获取的定位表达式: "
				+ locatorValue);
		// 根据locatorType变量值内容判断返回何种的定位方式的By对象。
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);

		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);

		} else if (locatorType.toLowerCase().equals("classname")
				|| locatorType.toLowerCase().equals("class")) {
			return By.className(locatorValue);
		} else if (locatorType.toLowerCase().equals("tagname")
				|| locatorType.toLowerCase().equals("tag")) {
			return By.tagName(locatorValue);

		} else if (locatorType.toLowerCase().equals("linktext")
				|| locatorType.toLowerCase().equals("link")) {
			return By.linkText(locatorValue);

		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);

		} else if (locatorType.toLowerCase().equals("cssselector")
				|| locatorType.toLowerCase().equals("css")) {
			return By.cssSelector(locatorValue);

		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);

		} else {
			throw new Exception("输入的locatorType没有在程序中定义：" + locatorType);
		}

	}
	
	
	
	
	
	
	
	
	
	
}
