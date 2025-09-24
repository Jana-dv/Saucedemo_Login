package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class basePage {
    WebDriver driver;
    WebDriverWait wait;

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public basePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
}
