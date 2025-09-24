package parent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.loginPage;
import pages.productsPage;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected loginPage loginPage;
    protected productsPage productsPage;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new loginPage(driver);
        productsPage = new productsPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
