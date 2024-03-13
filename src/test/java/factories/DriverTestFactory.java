package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverTestFactory {
    public static WebDriver driver = null;
    public static WebDriver getTestDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            if (browser.equalsIgnoreCase("safari")) {
                driver = new SafariDriver();
            } else {
                if (browser.equalsIgnoreCase("iexplore")) {
                    driver = new InternetExplorerDriver();
                } else {
                    if (browser.equalsIgnoreCase("firefox")) {
                        driver = new FirefoxDriver();
                    }
                }
            }
        }
        return driver;
    }
}