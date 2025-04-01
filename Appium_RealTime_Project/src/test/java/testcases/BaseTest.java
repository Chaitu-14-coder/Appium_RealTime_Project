package testcases;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utilities.ReadConfig;

public class BaseTest {
    protected WebDriver driver1;

    public WebDriver getDriver() {
        return driver;
    }


	public static AndroidDriver driver;
	public AppiumDriverLocalService service;
    public static Logger logger;
	ReadConfig config = new ReadConfig();
	
	public String platformName = config.getPlatformName();
	public String automationName = config.getAutomationName();
	public String deviceName = config.getDeviceName();
	public String app = config.getapp();
	public String platformVersion = config.getPlatformVersion();
	
	
	@BeforeTest
	public void configureAppium()  throws MalformedURLException, URISyntaxException {
		  logger = Logger.getLogger("General_Store_Application");
		  PropertyConfigurator.configure("./log4j.properties");
		
		  
		  logger.info("Appium Server started");
//		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Dell\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4724).build();
//		service.start();
		//ANdriodDriver
		//Appiumcode->Appiumserver->Mobile
		//DesiredCapabilities cap= new DesiredCapabilities();
		UiAutomator2Options Options= new UiAutomator2Options();
		Options.setDeviceName(deviceName);
		Options.setPlatformName(platformName);
		Options.setAutomationName(automationName);
		Options.setPlatformVersion(platformVersion);
		Options.setCapability("chromedriverExecutable", "C:\\Users\\DELL\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\chromedriver\\win\\chromedriver.exe");
		Options.setApp(app); 
			
		URL url = new URI("http://0.0.0.0:4723/wd/hub/").toURL();  //use when appium start manually
		//URL url = new URI("http://127.0.0.1:4724").toURL();
		 driver = new AndroidDriver(url, Options);
          
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@AfterTest
	public void teardown() {
		 logger.info("Appium Server ended");
		driver.quit();
		//stop server
		//service.stop();
	}
	
	
}
