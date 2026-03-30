package Page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ProductPage extends BasePage{
    WebDriver driver;
    String testName = "AmazonFlow_" + getDriver().getClass().getSimpleName();
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-button") 
    WebElement addToCartBtn;
    
    @FindBy(xpath = "//span[@id='desktop-ptc-button-celWidget']")
    WebElement proceedToCheckoutBtn;


    public void addtocart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        takeScreenshot(testName, "Step9_addtocart");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        System.out.println("Added to cart successfully.");
    }
    public void proceedtocheckout() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 takeScreenshot(testName, "Step11_paytoproceed");
         wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }
}
