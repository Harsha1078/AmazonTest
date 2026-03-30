package TestPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import base.BasePage;
import Page.HomePage;
import Page.ProductPage;
import Page.ResultPage;

public class TestingPage extends BasePage {
    @Test
    public void Testing() {
        WebDriver driver = getDriver(); 
        String testName = "AmazonFlow_" + getDriver().getClass().getSimpleName();
      

        HomePage home = new HomePage(driver);
        ResultPage result = new ResultPage(driver);
        ProductPage product = new ProductPage(driver);
        
        takeScreenshot(testName, "Step1_DismissPop");
        home.dismisspop();
        takeScreenshot(testName, "Step2_DismissPop");
        
        home.searchproduct("laptop");
        takeScreenshot(testName, "Step3_Search");
      
        
        result.selectbrand("Dell");
        takeScreenshot(testName, "Step5_selectbrand");
        
        result.selectram("16 GB");
        takeScreenshot(testName, "Step6_selectram");
        
        result.selectproduct();       
       
        product.addtocart();
        takeScreenshot(testName, "Step10_AddToCart");
        
        product.proceedtocheckout();
        takeScreenshot(testName, "Step12_PRoceedTOcheckout");
    }
}