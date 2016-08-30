package Basic;

import org.openqa.selenium.WebDriver;

import com.mis.market.pages.LoginPage;

public class Login {
	
	public static void execute(WebDriver driver,String Username,String Password) throws Exception{
		driver.get(Constant.Url);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.userName().sendKeys(Username);
		loginPage.passWord().sendKeys(Password);
		loginPage.loginButton().click();
		Thread.sleep(5000);
		driver.manage().window().maximize();
		
	}

}
