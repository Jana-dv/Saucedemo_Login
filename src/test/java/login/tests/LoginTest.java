package login.tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parent.BaseTest;
import user.User;

import static enums.DepartmentNaming.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Epic("Модуль приложения: Логин")
    @Feature("Для физических лиц")
    @Story("sl-1234")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Yana Baranovskaya")
    @TmsLink("Tests")
    @Issue("Tests")
    @Test(description = "Проверка корректной авторизации")
    public void checkCorrectLogin() {
        loginPage.open()
                .login(withAdminPermission());
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Название заголовка не соответсвует ожидаемому");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyUsername(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = ("loginData"), description = "Проверка авторизации с некорректными данными")
    public void checkIncorrectLogin(User user, String errorMessage) {
        loginPage.open()
                .login(user);
        assertEquals(loginPage.checkErrorMessage(), errorMessage);
    }
}
