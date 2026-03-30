package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    public static void setDriver(String browser) {
        String browserName = (browser == null) ? "chrome" : browser.toLowerCase();
        
        try {
            if (browserName.equals("chrome")) {
            
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                TL_DRIVER.set(new ChromeDriver(options));
            } else if (browserName.equals("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                TL_DRIVER.set(new EdgeDriver(options));
            } else {
                throw new RuntimeException("Unsupported browser: " + browserName);
            }
        } catch (Exception e) {
            System.err.println("CRITICAL: Failed to initialize " + browserName + ". Error: " + e.getMessage());
            throw e; 
        }
    }

    public static WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            TL_DRIVER.remove();
        }
    }
}