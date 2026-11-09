package ru.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//h2[text()='Восстановление пароля']")
    private WebElement header;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти на страницу входа по ссылке «Войти»")
    public LoginPage clickLoginLink() {
        waitClickable(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Проверить, что открыта страница восстановления пароля")
    public boolean isOpened() {
        return waitVisible(header).isDisplayed();
    }
}