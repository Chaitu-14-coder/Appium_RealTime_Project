package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig() {
		File src = new File("./Configuration/Config.properties");
		try {
			FileInputStream Fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(Fis);
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	 public String getPlatformName() {
		return pro.getProperty("platformName");
		}
	public String getAutomationName() {
		return pro.getProperty("automationName");
		}
	public String getDeviceName() {
		return pro.getProperty("deviceName");
		}
	public String getapp() {
		return pro.getProperty("app");
		}
	public String getPlatformVersion() {
		return pro.getProperty("platformVersion");
		}
	public String getUrl() {
		return pro.getProperty("url");
		}
	
	
	
	public String getenv() {
		return pro.getProperty("env");
		}
	public String getAppLanguage() {
		return pro.getProperty("appLanguage");
		}
	public String getAppName() {
		return pro.getProperty("appName");
		}
	public String getBrowserName() {
		return pro.getProperty("browserName");
		
	}
	public String getresourceId() {
		return pro.getProperty("resourceId");
		
	}
}
