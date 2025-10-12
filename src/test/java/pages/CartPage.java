package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage {
    final By title = By.xpath("//span[@data-test='title']");
    final By title2 = By.xpath("//*[text()='Your Cart']");
    static final By ITEM_NAME = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем название заголовка страницы")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Проверяем, что название заголовка отображается")
    public boolean isTitlePresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title2)).isDisplayed();
    }

    @Step("Проверяем содержимое корзины")
    public List<String> getProductsNames() {
        List<WebElement> allProductsNames = driver.findElements(ITEM_NAME);
        List<String> names = new ArrayList<>();
        for (WebElement product : allProductsNames) {
            names.add(product.getText());
        }
        return names;
    }
}
