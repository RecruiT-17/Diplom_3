package ru.stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellar.base.BaseTest;
import ru.stellar.pages.*;
import ru.stellar.utils.UserGenerator;

@Epic("Stellar Burgers")
@Feature("Вход в аккаунт")
public class LoginTest extends BaseTest {

    private String email;
    private String password;

    @Before
    public void createUser() {
        email = UserGenerator.randomEmail();
        password = "password123";

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.register(UserGenerator.randomName(), email, password);

        driver.get(config.getProperty("base.url"));
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Проверяем вход через кнопку на главной странице")
    public void loginViaMainPageButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        MainPage afterLogin = loginPage.login(email, password);

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
        MainPage afterLogin = loginPage.login(email, password);

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
        MainPage afterLogin = loginFromRegister.login(email, password);

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
        MainPage afterLogin = loginFromForgot.login(email, password);

        Assert.assertTrue("После входа должна открыться главная страница",
                afterLogin.isConstructorDisplayed());
    }
}