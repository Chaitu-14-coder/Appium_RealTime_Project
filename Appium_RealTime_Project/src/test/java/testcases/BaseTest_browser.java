package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.options.app.SupportsAppPackageOption;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utilities.ReadConfig;

public class BaseTest_browser {

	  public static WebDriver driver;
	public AppiumDriverLocalService service;
    public static Logger logger;
	ReadConfig config = new ReadConfig();
	
	public String platformName = config.getPlatformName();
	public String automationName = config.getAutomationName();
	public String deviceName = config.getDeviceName();
	public String app = config.getapp();
	public String platformVersion = config.getPlatformVersion();
	public String browserName = config.getBrowserName();
	
	@BeforeTest
	public void configureAppium()  throws MalformedURLException, URISyntaxException {
		  logger = Logger.getLogger("UnosysPotal");
		  PropertyConfigurator.configure("./log4j.properties");
		
		  
		  logger.info("Appium Server started");
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Dell\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4724).build();
		service.start();
		//ANdriodDriver
		//Appiumcode->Appiumserver->Mobile
		DesiredCapabilities cap= new DesiredCapabilities();
		UiAutomator2Options Options= new UiAutomator2Options();
		Options.setCapability("deviceName" , deviceName );
		Options.setCapability("browser_name", browserName);
//		Options.setCapability("platformName" , platformName );
//		Options.setCapability("automationName" , automationName );
//		
//		Options.setCapability("platformVersion" , platformVersion );
//		
		//Options.setCapability("app", "D:\\Chaitali_framworks\\Appium_Demo\\src\\test\\java\\resource\\ApiDemos-debug.apk");
			
		URL url = new URI("http://127.0.0.1:4724/").toURL();
          driver= new AndroidDriver(url, Options);
          
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
		//stop server
		service.stop();
	}
	
	
}
