package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Actions;

public class Cart_page extends Actions{

WebDriver ldriver;

//Constructor
	public Cart_page(WebDriver rdriver) {
		ldriver=rdriver;
	    PageFactory.initElements(rdriver, this);
}
	
//Find all elements which is locate to CartPage	
@FindBy(id = "com.androidsample.generalstore:id/productPrice")
@CacheLookup List<WebElement> productPrices ;	


@FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
@CacheLookup WebElement totalAmout ;

@FindBy(className  = "android.widget.CheckBox")
@CacheLookup  WebElement checkBox ;

@FindBy(id  = "com.androidsample.generalstore:id/toolbar_title")
@CacheLookup  WebElement cartPage ;

@FindBy(id  = "com.androidsample.generalstore:id/termsButton")
@CacheLookup WebElement termCondition ;

@FindBy(id  = "android:id/button1")
@CacheLookup WebElement Close ;


@FindBy(id  = "com.androidsample.generalstore:id/btnProceed")
@CacheLookup WebElement proceed ;
	
public List<WebElement> add_To_Cart() 
{
	return productPrices;
	 
} 
                             
public WebElement totalAmount() 
{
	return totalAmout;
	 
}

public WebElement CartTitile() 
{
	return cartPage;
	 
}

public void chekBox_action() {
	checkBox.click();	
}

public void term_conditions()
{
	longPres_element(termCondition);
}


public void close_popup()
{
	Close.click();
}


public void process_application()
{
	proceed.click();
}

}
