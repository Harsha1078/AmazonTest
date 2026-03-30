package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy (name="field-keywords") WebElement searchbar;
	
	@FindBy (xpath="//div/span/span/input[@data-action-type='DISMISS']") WebElement dismisspopup;
	
	@FindBy (xpath="//div[@class='nav-search-submit nav-sprite']") WebElement searchbtn;
	public void dismisspop() {
		 try {
			 if(dismisspopup !=null && dismisspopup.isDisplayed()) {
				 dismisspopup.click();
				 System.out.println("POP Dismissed");
			 }
		 }
		 catch(Exception e) {
		 }
	 } 
	 public void searchproduct(String product){
		 searchbar.clear();
		 searchbar.sendKeys(product);
		 System.out.println("Product Name Entered.");
		 searchbtn.click();
		 System.out.println("Search Button Clicked");	 
	 }
}

