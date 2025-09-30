package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage {
    static final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsNames() {
        List<WebElement> allProductsNames = driver.findElements(ITEM_NAME);
        List<String> names = new ArrayList<>();
        for (WebElement product : allProductsNames) {
            names.add(product.getText());
        }
        return names;
    }
}
