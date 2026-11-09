package ru.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameInput;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(@class,'input__error')]")
    private WebElement passwordError;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//h2[text()='Регистрация']")
    private WebElement registerHeader;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя: {name}")
    public RegisterPage setName(String name) {
        waitVisible(nameInput).clear();
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Ввести email: {email}")
    public RegisterPage setEmail(String email) {
        waitVisible(emailInput).clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public RegisterPage setPassword(String password) {
        waitVisible(passwordInput).clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Снять фокус с поля пароля (для срабатывания валидации)")
    public RegisterPage blurPasswordField() {
        passwordInput.sendKeys(org.openqa.selenium.Keys.TAB);
        return this;
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public void clickRegister() {
        waitClickable(registerButton).click();
    }

    @Step("Зарегистрировать пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return waitVisible(passwordError).getText();
    }

    @Step("Проверить, что отображается ошибка пароля")
    public boolean isPasswordErrorDisplayed() {
        return passwordError.isDisplayed();
    }

    @Step("Перейти на страницу входа по ссылке")
    public LoginPage clickLoginLink() {
        waitClickable(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Проверить, что открыта страница регистрации")
    public boolean isRegisterPageOpened() {
        return waitVisible(registerHeader).isDisplayed();
    }
}