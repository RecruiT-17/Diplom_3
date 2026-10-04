package ru.stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellar.base.BaseTest;
import ru.stellar.pages.LoginPage;
import ru.stellar.pages.MainPage;
import ru.stellar.pages.RegisterPage;
import ru.stellar.utils.UserGenerator;

@Epic("Stellar Burgers")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("После регистрации пользователь может войти с теми же данными")
    public void successRegistrationTest() {
        String name = UserGenerator.randomName();
        String email = UserGenerator.randomEmail();
        String password = "password123";

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.register(name, email, password);

        driver.get(config.getProperty("base.url"));
        MainPage freshMain = new MainPage(driver);
        LoginPage freshLogin = freshMain.clickLoginButton();
        MainPage afterLogin = freshLogin.login(email, password);

        Assert.assertTrue("После успешного входа пользователь должен быть авторизован",
                afterLogin.isUserAuthorized());
    }

    @Test
    @DisplayName("Ошибка при вводе пароля короче 6 символов")
    @Description("При вводе пароля из 5 символов отображается сообщение об ошибке")
    public void shortPasswordErrorTest() {
        String name = UserGenerator.randomName();
        String email = UserGenerator.randomEmail();
        String shortPassword = "12345";

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(shortPassword);
        registerPage.blurPasswordField();

        Assert.assertTrue("Должна отображаться ошибка о некорректном пароле",
                registerPage.isPasswordErrorDisplayed());

        String errorText = registerPage.getPasswordErrorText();
        Assert.assertTrue(
                "Ожидалось сообщение о некорректном пароле, получено: " + errorText,
                errorText.toLowerCase().contains("пароль")
        );
    }
}