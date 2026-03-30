package base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Driver.DriverFactory;

public class BasePage {

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        
        
      
        DriverFactory.setDriver(browser);
        
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.com/");
    }

    @AfterMethod
    public void teardown() {
       
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public String takeScreenshot(String testName, String step) {
        try {
            WebDriver driver = getDriver();
            if (driver == null) {
                System.err.println("Screenshot skip: Driver is null for thread " + Thread.currentThread().getId());
                return null;
            }

           
            String time = new SimpleDateFormat("HHmm_ssSSS").format(new Date());
            
          
            String projectPath = System.getProperty("user.dir");
            String folderPath = projectPath + File.separator + "screenshots" + File.separator + sanitize(testName);
            
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); 
            }

            String fileName = sanitize(step) + "_" + time + ".png";
            File dest = new File(folder, fileName);
         
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, dest);

            System.out.println("SUCCESS: Screenshot saved at: " + dest.getAbsolutePath());
            
            return dest.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("ERROR: Could not take screenshot: " + e.getMessage());
            return null;
        }
    }

    private String sanitize(String s) {
        if (s == null) return "null";
        return s.trim().replaceAll("[^a-zA-Z0-9_]", "_");
    }
        
}