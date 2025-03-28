package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Actions;

public class Product_page extends Actions {
WebDriver ldriver;

//Constructor
    public Product_page(WebDriver rdriver) {
				ldriver=rdriver;
			    PageFactory.initElements(rdriver, this);
}
			
//Find all elements which is locate to ProductPage	
 @FindBy(id = "com.androidsample.generalstore:id/rvProductList")
 @CacheLookup WebElement resourceId;
 
 @FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
 @CacheLookup WebElement producttitle;
 
 
 @FindBy(id = "com.androidsample.generalstore:id/productName")
 @CacheLookup WebElement productName ;
   
 
 @FindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])")
 @CacheLookup List<WebElement> addToCart;
 
 
 @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
 @CacheLookup WebElement btn_cart;
 
 
 @FindBy(id = "com.androidsample.generalstore:id/productName")
 @CacheLookup List<WebElement> Products;

   
 public void scroll_upto_Product(String productName , String resourceID) 
{
	scroll_upto_element_whiletext_visible(productName,resourceID );	 
	 
}
 
 public List<WebElement> ProductList() 
{
	return Products;
	 
}
 
 public List<WebElement> add_To_Cart() 
{
	return addToCart;
	 
} 

 public void cart_page() {
	 btn_cart.click();
 }
 
 public WebElement Producttitle() 
{
	return producttitle;
	 
}  
    
    
    
}
