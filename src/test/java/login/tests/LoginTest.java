package login.tests;

import org.testng.annotations.DataProvider;
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

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void checkIncorrectLogin(String username, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(username, password);
        assertEquals(loginPage.checkErrorMessage(), errorMessage);
    }

    @Test(description = "Добавление товара в корзину")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
    }
}
