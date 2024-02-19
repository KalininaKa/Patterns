package tests;

import factories.DriverTestFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static factories.DriverTestFactory.driver;

public class FactoryTests {

    //тест с использованием разных браузеров
    @Test
    public void safariTest() {
        driver = DriverTestFactory.getTestDriver("safari");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wikipedia.org/");
        String title = driver.getTitle();
        Assertions.assertEquals("Wikipedia", title);

    }
    @Test
    public void chromeTest() {
        driver = DriverTestFactory.getTestDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wikipedia.org/");
        String title = driver.getTitle();
        Assertions.assertEquals("Wikipedia", title);
    }

    @Test
    public void iexploreTest() {
        driver = DriverTestFactory.getTestDriver("iexplore");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wikipedia.org/");
        String title = driver.getTitle();
        Assertions.assertEquals("Wikipedia", title);
    }

    @Test
    public void firefoxTest() {
        driver = DriverTestFactory.getTestDriver("firefox");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wikipedia.org/");
        String title = driver.getTitle();
        Assertions.assertEquals("Wikipedia", title);
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}