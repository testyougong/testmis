package Basic;

import org.openqa.selenium.WebDriver;

import com.mis.market.pages.LoginPage;

public class Login {
	
	public static void execute(WebDriver driver,String userName,String passWord) throws Exception{
		driver.get(Constant.Url);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.userName().sendKeys(Constant.UserName);
		loginPage.passWord().sendKeys(Constant.PassWord);
		loginPage.loginButton().click();
		Thread.sleep(5000);
		driver.manage().window().maximize();
		
	}

}
