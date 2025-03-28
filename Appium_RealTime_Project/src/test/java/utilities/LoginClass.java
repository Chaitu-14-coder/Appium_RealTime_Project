package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import pages.Home_page;
import testcases.BaseTest;

public class LoginClass extends BaseTest {
	
	public static void Login_Test_positive() throws InterruptedException 
	{
			Home_page homepage = new Home_page(driver);
			driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			homepage.EnterName("Pooja mishra");
			homepage.selectRdioBtn();
			homepage.selectcountry();
			homepage.submit();
			Thread.sleep(2000);
			
	}
}
