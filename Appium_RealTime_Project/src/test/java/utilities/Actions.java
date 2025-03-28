package utilities;

import java.lang.annotation.ElementType;
import java.time.Duration;

import javax.lang.model.element.Element;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsRotation;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import testcases.BaseTest;

public class Actions extends BaseTest {
       
public void dragdrop(WebElement ele) 
{
		 System.out.println("webelement in dragdrop :" + ele);
		 RemoteWebElement remoteElement = (RemoteWebElement) ele;
				((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
					    "elementId", remoteElement.getId(),
					    "endX", 667,
					    "endY", 600
				));
}
	
	
public void dragDrop_element(WebElement ele , WebElement secondEle)
{
		TouchAction t= new TouchAction((PerformsTouchActions) driver);
		t.longPress(element(ele)).moveTo(element(secondEle)).release().perform();
}
	

public void longPress(WebElement ele) 
{	
			 ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
					 ImmutableMap.of("elementId",((RemoteWebElement)ele).getId() , "duration" , 2000));
}


public void longPres_element(WebElement ele) 
{	
	    int x = ele.getLocation().getX();
	    int y = ele.getLocation().getY();

	    // Use Appium's TouchAction to perform a long press gesture
	    new TouchAction(driver)
	        .longPress(PointOption.point(x, y)) // Long press on the element's coordinates
	        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))) // 2-second press
	        .release()
	        .perform();
		
}
	
	

public  void scrollDown() 
{
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    )
				);
		}while(canScrollMore);
}


public void scroll_upto_element(String ele) {
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ele+"\"));"));
}

public void scroll_upto_element_whiletext_visible(String ele , String resourceID) {
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+resourceID+"\")).scrollIntoView(new UiSelector().textMatches(\""+ele+"\").instance(0))"));
}
	
	

public void swipeWindow(WebElement ele) 
{
		 ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",  ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
				 "direction","left", "percent" , 0.75));
}
	
	

public void swipeGesture(WebElement ele,WebElement secondEle) 
{	
		TouchAction t= new TouchAction((PerformsTouchActions) driver);
		t.longPress(LongPressOptions.longPressOptions().withElement(element(ele)).withDuration(ofSeconds(2))).moveTo(element(secondEle)).release().perform();
		
}
	
	

public void landscapeMode() 
{
		 DeviceRotation ladndscape= new DeviceRotation(0, 0, 90);
         ((SupportsRotation) driver).rotate(ladndscape);
}


public double getAmount(String value) {
	value=value.substring(1);
	double amountValue=Double.parseDouble(value);
	return amountValue;
}	
	
}
