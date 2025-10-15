package login.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import parent.BaseTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static enums.DepartmentNaming.CART;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

import static org.testng.Assert.assertEquals;

public class AddGoodsToCartTest extends BaseTest {
    @Epic("Модуль приложения: Корзина")
    @Feature("Для физических лиц")
    @Story("sl-1236")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yana Baranovskaya")
    @TmsLink("Tests")
    @Issue("Tests")
    @Test(description = "Добавление товара в корзину")
    public void checkAddToCart() {
        loginPage.open()
                .login(withAdminPermission());
        productsPage.addToCart("Sauce Labs Fleece Jacket");
    }

    @Test(description = "Добавление товара в корзину и проверка содержимого корзины")
    public void checkGoodsInCart() {
        loginPage.open()
                .login(withAdminPermission());
        //Определяем количество товаров для добавления в корзину (рандомный выбор)
        int cartSize = ThreadLocalRandom.current().nextInt(1, productsPage.getProductQuantity());
        //Формируем лист из рандомных уникальных имен товаров
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
        assertTrue(cartPage.isTitlePresent());
        assertEquals(cartPage.getTitle(), CART.getDisplayName(), "Название заголовка не соответсвует ожидаемому");
        List<String> productsInCart = cartPage.getProductsNames(); //Формируем лист из названий товаров в корзине
        assertEquals(productsToAdd, productsInCart, "Названия товаров не совпадают");
    }
}
