package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class productsPage extends basePage {
    final By title = By.xpath("//span[@data-test='title']");
    final By title2 = By.xpath("//*[text()='Products']");
    private static final String ADD_TO_CART_BUTTON_PATTERN =
            "//*[text() = '%s']//ancestor::div[@class = 'inventory_item']//child::button[text() = 'Add to cart']";

    public productsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public boolean isTitlePresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title2)).isDisplayed();
    }

    public void addToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_BUTTON_PATTERN.formatted(goodsName));
        driver.findElement(addToCart).click();
    }
}
