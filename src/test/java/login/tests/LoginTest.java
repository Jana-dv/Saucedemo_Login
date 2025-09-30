package login.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parent.BaseTest;
import utils.PropertyReader;

import static org.testng.Assert.*;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test(description = "Проверка корректной авторизации")
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), "Products", "Название заголовка не соответсвует ожидаемому");
    }

    @DataProvider()
    public Object[][] loginData() {
        locked_user = PropertyReader.getProperty("saucedemo.lockeduser");
        user = PropertyReader.getProperty("saucedemo.user");
        password = PropertyReader.getProperty("saucedemo.password");
        return new Object[][]{
                {locked_user, password, "Epic sadface: Sorry, this user has been locked out."},
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void checkIncorrectLogin(String username, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.checkErrorMessage(), errorMessage);
    }

    @Test(description = "Добавление товара в корзину")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart("Sauce Labs Fleece Jacket");
    }
}
