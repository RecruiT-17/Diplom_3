package ru.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//h2[text()='Вход']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email: {email}")
    public LoginPage setEmail(String email) {
        waitVisible(emailInput).clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage setPassword(String password) {
        waitVisible(passwordInput).clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку «Войти»")
    public MainPage clickLogin() {
        waitClickable(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Выполнить вход с email={email}")
    public MainPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        return clickLogin();
    }

    @Step("Перейти на страницу регистрации")
    public RegisterPage clickRegisterLink() {
        waitClickable(registerLink).click();
        return new RegisterPage(driver);
    }

    @Step("Перейти на страницу восстановления пароля")
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitClickable(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Проверить, что открыта страница входа")
    public boolean isLoginPageOpened() {
        return waitVisible(loginHeader).isDisplayed();
    }
}