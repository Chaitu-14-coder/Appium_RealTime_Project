package testcases;

import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.Cart_page;
import pages.Home_page;
import pages.Product_page;
import utilities.Actions;
import utilities.LoginClass;

public class Ecommerce_tc_01 extends BaseTest{
	public String resourceID = config.getresourceId();
	
	@SuppressWarnings("deprecation")
	@Test 
public static void Login_Test_nigative() throws InterruptedException
{
		logger.debug("Started Invalid Login Test");
		Home_page homepage = new Home_page(driver);
		driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
		LoginClass login = new LoginClass();
		login.Login_Test_positive();
		homepage.selectRdioBtn();
		logger.info("User select redio button");
		homepage.selectcountry();
		logger.info("User select Country");
		homepage.submit();
		logger.info("User click on Login button");
		Thread.sleep(2000);
		Assert.assertEquals("Please enter your name", homepage.Errormessage()); //Actual Validation
		logger.error("Error message appears" + homepage.Errormessage());
}
	

	
	@SuppressWarnings("deprecation")
	@Test (dependsOnMethods = "Login_Test_nigative")
public void Login_Test() throws InterruptedException 
{ 
	Home_page homepage = new Home_page(driver);
	Cart_page cartPage = new Cart_page(driver);
	Product_page productpage = new Product_page(driver);
    Actions action = new Actions();
	LoginClass login = new LoginClass();
//	
//	logger.debug("Started Login Test");
//	login.Login_Test_positive();
	try{
	String productTitle = productpage.Producttitle().getText();
	Assert.assertEquals("Products", productTitle);  //Login successful validations
	logger.info("User Login Sucessfully");
	}catch (AssertionError e) {
		logger.error("Failed Login Test : " + e.getMessage());
	}
	
	
	driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
	Thread.sleep(2000);
	productpage.scroll_upto_Product("Jordan 6 Rings" , resourceID);
	logger.info("User Perform Scrolling action");
	List<WebElement> productList = productpage.ProductList();
	List<WebElement> addToCart = productpage.add_To_Cart();

	
    int count = productList.size();
	for(int i=0;i<count;i++) {
		String productName=productList.get(i).getText();	
		if(!productName.equalsIgnoreCase("Jordan 6 Rings")) {
			addToCart.get(i).click();
		}
		if(productName.equalsIgnoreCase("Jordan 6 Rings")) {
			addToCart.get(i).click();
		}
	}
	logger.info("User Select Products");
	Thread.sleep(2000);
	productpage.cart_page();
	try{
		Thread.sleep(2000);
		String cartTitle = cartPage.CartTitile().getText();
		Assert.assertEquals("Products", cartTitle); 
		logger.info("Added Products to cart Sucessfully");
		}catch (AssertionError e) {
			logger.error("Failed to add products :"+e.getMessage());
		}
	Thread.sleep(2000);
	
	List<WebElement> productPrices = cartPage.add_To_Cart();
	int count1=productPrices.size();
	logger.info("Count of Added Products is :" + count1);
	double sum = 0;
	for(int i=0;i<count1;i++) {
		String amount1=productPrices.get(i). getText();
		System.out.println("Amount1 price is :"+amount1);
		double amountValue =action.getAmount(amount1);
		sum=sum+amountValue;
	}
	logger.info("Total amount of added Products is :" + sum);
	WebElement totalPrice = cartPage.totalAmount();
	String totalamt=totalPrice.getText();
	double TotalValue = action.getAmount(totalamt);
	logger.info("Total Price of Products is :" + TotalValue);
	
	try {
		Assert.assertEquals(sum, TotalValue);
		logger.info("Sum and Total Prices matched sucessfully");
	}catch (AssertionError e) {
		logger.error("Amount not match :" + e.getMessage());
	}
	
	
	cartPage.chekBox_action();
	logger.info("Check box Selected");
	cartPage.term_conditions();
	logger.info("Accepted Term and Conditions");	
	//cartPage.close_popup();
	logger.info("Close Popup");
	cartPage.process_application();
	logger.info("Products Successfully perchase");
	Thread.sleep(2000);		
}
	
}
