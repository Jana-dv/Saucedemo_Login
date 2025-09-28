package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    final By title = By.xpath("//span[@data-test='title']");
    final By title2 = By.xpath("//*[text()='Products']");
    //private static final By ADD_TO_CART_BUTTON = By.xpath("//*[text()='Add to cart']");
    private static final String ADD_TO_CART_BUTTON_PATTERN =
            "//*[text() = '%s']//ancestor::div[@class = 'inventory_item']//child::button[text() = 'Add to cart']";

    public ProductsPage(WebDriver driver) {
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

    public void openCart() {
        driver.findElement(By.xpath("//*[@data-test='shopping-cart-link']")).click();
    }

    public String getProductName(int goodIndex) {
        return driver.findElements(By.cssSelector(".inventory_item_name")).get(goodIndex).getText();
    }
// Определяем количество товаров на странице каталога ("Products")
    public int getProductQuantity() {
        return driver.findElements(By.cssSelector(".inventory_item_name")).size();
    }
}

