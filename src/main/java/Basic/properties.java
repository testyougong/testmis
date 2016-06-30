package Basic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class properties {
	protected static String url;
	protected static String email;	
	protected static String password;
	
	public properties() throws IOException{
		Properties pro = new Properties();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/zhouxin/Documents/workspace/mis/src/main/java/properties/market.properties")); 
		pro.load(in);
		
		properties.url = pro.getProperty("url");
		properties.email = pro.getProperty("email");
		properties.password = pro.getProperty("password");		
	}
	
}
