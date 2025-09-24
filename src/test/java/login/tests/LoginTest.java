package login.tests;

import org.testng.annotations.Test;
import parent.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка корректной авторизации")
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), "Products", "Название заголовка не соответсвует ожидаемому");
    }

    @Test(description = "Провекрка регистрации заблокированного юзера")
    public void checkLockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(loginPage.checkErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "Проверка авторизации без ввода Username")
    public void checkNoUsernameLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.checkErrorMessage(), "Epic sadface: Username is required", "Текст ошибки не совпадает с ожидаемым");
    }

    @Test(description = "Проверка авторизации без ввода Password")
    public void checkNoPasswordLogin() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.checkErrorMessage(), "Epic sadface: Password is required", "Текст ошибки не совпадает с ожидаемым");
    }

    @Test(description = "Добавление товара в корзину")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
    }
}
