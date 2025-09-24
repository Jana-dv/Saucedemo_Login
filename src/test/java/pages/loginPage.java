package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends basePage {

    public loginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USERNAME_INPUT = By.xpath("//*[@placeholder='Username']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@placeholder='Password']");
    private static final By LOGIN_BUTTON = By.cssSelector(".submit-button");
    private static final By ERROR = By.xpath("//h3[@data-test='error']");

    public void open() {
        driver.get(BASE_URL + "inventory.html");

    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String checkErrorMessage() {
        return driver.findElement(ERROR).getText();
    }
}
