package LoginTests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import parent.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void standardUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        boolean isPresent = browser.findElement(By.cssSelector(".title")).isDisplayed();
        assertTrue(isPresent);
    }

    @Test
    public void blockedUserTest() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        String errorMsg = browser.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(errorMsg, "Epic sadface: Sorry, this user has been locked out.");
    }
}
