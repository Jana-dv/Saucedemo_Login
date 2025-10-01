package login.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parent.BaseTest;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
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
        return new Object[][]{
                {withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyUsername(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void checkIncorrectLogin(User user, String errorMessage) {
        loginPage.open();
        loginPage.login(user);
        assertEquals(loginPage.checkErrorMessage(), errorMessage);
    }

    @Test(description = "Добавление товара в корзину")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart("Sauce Labs Fleece Jacket");
    }
}
