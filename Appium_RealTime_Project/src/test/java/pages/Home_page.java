package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Actions;

public class Home_page extends Actions {
private WebDriver ldriver;

//Constructor
		public Home_page(WebDriver rdriver) {
			this.ldriver=rdriver;
		    PageFactory.initElements(rdriver, this);
}
		
//Find all elements which is locate to HomePage
@FindBy (id="com.androidsample.generalstore:id/nameField") 
@CacheLookup public WebElement name;

@FindBy (id="com.androidsample.generalstore:id/radioFemale") 
@CacheLookup public WebElement radioButton;

@FindBy (id="com.androidsample.generalstore:id/spinnerCountry") 
@CacheLookup public WebElement countryDropDown;

@FindBy (id="com.androidsample.generalstore:id/btnLetsShop")
@CacheLookup public WebElement submit;

@FindBy(xpath = "//android.widget.TextView[@text='Aruba']") // Finding element after scroll
@CacheLookup public WebElement countryName;

@FindBy(xpath="//android.widget.Toast[1]") 
@CacheLookup public WebElement ToastMessage;




//Enter name 
public void EnterName(String nameToEnter) {
    name.sendKeys(nameToEnter);
}

public void selectRdioBtn() {
	radioButton.click();
}

public void selectcountry() {
	countryDropDown.click();
	scroll_upto_element("Aruba");
	countryName.click();
}

public void submit() {
	submit.click();
}

public String Errormessage() {
      return ToastMessage.getText();
}
		
}
