package ru.stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellar.base.BaseTest;
import ru.stellar.pages.ForgotPasswordPage;
import ru.stellar.pages.LoginPage;
import ru.stellar.pages.MainPage;
import ru.stellar.pages.RegisterPage;
import ru.stellar.utils.UserFixture;

@Epic("Stellar Burgers")
@Feature("Вход в аккаунт")
public class LoginTest extends BaseTest {

    private UserFixture user;

    @Before
    public void createUserViaApi() {
        user = UserFixture.createUnique(apiClient);

        driver.get(config.getProperty("base.url"));
    }

    @After
    public void deleteUserViaApi() {
        if (user != null) {
            user.delete(apiClient);
        }
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Проверяем вход через кнопку на главной странице")
    public void loginViaMainPageButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        MainPage afterLogin = loginPage.login(user.email, user.password);

        Assert.assertTrue("После входа должна открыться главная страница",
                afterLogin.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверяем вход через ссылку на личный кабинет")
    public void loginViaPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        MainPage afterLogin = loginPage.login(user.email, user.password);

        Assert.assertTrue("После входа должна открыться главная страница",
                afterLogin.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяем вход через ссылку «Войти» на странице регистрации")
    public void loginViaRegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        LoginPage loginFromRegister = registerPage.clickLoginLink();
        MainPage afterLogin = loginFromRegister.login(user.email, user.password);

        Assert.assertTrue("После входа должна открыться главная страница",
                afterLogin.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяем вход через ссылку «Войти» на странице восстановления пароля")
    public void loginViaForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        ForgotPasswordPage forgotPage = loginPage.clickForgotPasswordLink();
        LoginPage loginFromForgot = forgotPage.clickLoginLink();
        MainPage afterLogin = loginFromForgot.login(user.email, user.password);

        Assert.assertTrue("После входа должна открыться главная страница",
                afterLogin.isConstructorDisplayed());
    }
}