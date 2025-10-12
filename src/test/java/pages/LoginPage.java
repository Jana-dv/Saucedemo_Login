package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USERNAME_INPUT = By.xpath("//*[@placeholder='Username']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@placeholder='Password']");
    private static final By LOGIN_BUTTON = By.cssSelector(".submit-button");
    private static final By ERROR = By.xpath("//h3[@data-test='error']");

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под кредами пользователя: логин = {user.email}, пароль = *****")
    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        clickSubmit();
    }

    public void fillLoginField(String username) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String checkErrorMessage() {
        return driver.findElement(ERROR).getText();
    }
}
