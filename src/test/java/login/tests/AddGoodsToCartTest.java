package login.tests;

import org.testng.annotations.Test;
import parent.BaseTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static user.UserFactory.*;

import static org.testng.Assert.assertEquals;

public class AddGoodsToCartTest extends BaseTest {

    @Test
    public void checkGoodsInCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        //Определяем количество товаров для добавления в корзину (рандомный выбор)
        int cartSize = ThreadLocalRandom.current().nextInt(1, productsPage.getProductQuantity());
        // Формируем лист из рандомных уникальных имен товаров
        List<String> productsToAdd = Stream.generate(() -> ThreadLocalRandom.current().nextInt(0, productsPage.getProductQuantity()))
                .distinct()
                .limit(cartSize)
                .map(productsPage::getProductName)
                .toList();
        //Итерируемся по списку productsToAdd и добавляем каждый элемент списка в корзину
        for (String productName : productsToAdd) {
            productsPage.addToCart(productName);
        }

        productsPage.openCart();
        List<String> productsInCart = cartPage.getProductsNames(); //Формируем лист из названий товаров в корзине
        assertEquals(productsToAdd, productsInCart, "Названия товаров не совпадают");
    }
}
