package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ResultPage extends BasePage{
	WebDriver driver;
	String testName = "AmazonFlow_" + getDriver().getClass().getSimpleName();
	public ResultPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//div[@id='brandsRefinements']//li")List <WebElement> brandlist;
	
	@FindBy(xpath="//div[@id='p_n_g-1003119721111/23720416011']//li") List <WebElement> ramlist;
	
	@FindBy(xpath="//a[@aria-label='Go to next page, page 2']") WebElement gotonxtpage;
	
	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
    List<WebElement> searchResults;
	
	@FindBy(xpath="//div[@data-component-type='s-search-result']") List <WebElement> Resultcount; 
	
	@FindBy(xpath=".//span[@class='a-icon-alt']") List<WebElement> rating;
	
	@FindBy(xpath="//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay apex-pricetopay-value']") List<WebElement> priceElement;
	
	@FindBy(xpath="//span[@class='a-price']") List<WebElement> prices;
	
	
	public void selectbrand(String brand){
		for(WebElement item: brandlist) {
			if(item.getText().trim().equalsIgnoreCase(brand)) {
				takeScreenshot(testName, "Step4_selectbrand");
				item.findElement(By.xpath(".//a")).click();
                System.out.println("Brand selected");
                break;
			}
		}
		
	}
	public void selectram(String size) {
		for(WebElement ram: ramlist) {
			if(ram.getText().trim().equalsIgnoreCase(size)) {
				
				ram.findElement(By.xpath(".//a")).click();
				System.out.println("Ram selected");
                break;
			}
		}
	}
	public void goToNextPage() {
		gotonxtpage.click();
		driver.navigate().back();
		System.out.println("goto back");
    }
	public void selectproduct() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   Actions actions = new Actions(driver);

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-component-type='s-search-result']")));

	    for (int i = 0; i < 20; i++) {
	        try {
	            List<WebElement> results = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
	            if (i >= results.size()) break;

	            WebElement res = results.get(i);

	         
	            List<WebElement> priceCheck = res.findElements(By.xpath(".//span[@class='a-price-whole']"));
	            if (priceCheck.isEmpty()) {
	                System.out.println("Skipping index " + i + ": No visible price.");
	                continue;
	            }

	           
	            List<WebElement> links = res.findElements(By.xpath(".//h2//a | .//a[contains(@class,'a-link-normal')]"));
	            if (links.isEmpty()) {
	            	takeScreenshot(testName, "Step7_checking for another product");
	                System.out.println("Skipping index " + i + ": Link not found.");              
	                continue;
	            }

	            WebElement targetLink = links.get(0);

	           
	            actions.moveToElement(targetLink).perform();
	            System.out.println("Price found at index " + i + ". Clicking product...");
	            takeScreenshot(testName, "Step8_selectprodlink");
	            targetLink.click();

	          
	            try {
	                new WebDriverWait(driver, Duration.ofSeconds(5))
	                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
	                System.out.println("Product page loaded with Add to Cart button!");
	                return; 
	            } catch (Exception e) {
	                System.out.println("Product page missing Add to Cart. Navigating back...");
	                driver.navigate().back();
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-component-type='s-search-result']")));
	            }

	        } catch (Exception e) {
	            System.out.println("Error at index " + i + ": " + e.getMessage());
	        }
	    }
	    throw new RuntimeException("Could not find a valid product with a price and Add to Cart button.");
	}
		}

