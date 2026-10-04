package ru.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//h1[contains(text(),'Соберите бургер')]")
    private WebElement constructorHeader;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку «Войти в аккаунт» на главной")
    public LoginPage clickLoginButton() {
        waitClickable(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Открыть раздел «Булки»")
    public MainPage clickBunsTab() {
        waitClickable(bunsTab).click();
        return this;
    }

    @Step("Открыть раздел «Соусы»")
    public MainPage clickSaucesTab() {
        waitClickable(saucesTab).click();
        return this;
    }

    @Step("Открыть раздел «Начинки»")
    public MainPage clickFillingsTab() {
        waitClickable(fillingsTab).click();
        return this;
    }

    @Step("Проверить, что активен раздел «Булки»")
    public boolean isBunsTabActive() {
        return wait.until(ExpectedConditions
                .attributeContains(bunsTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что активен раздел «Соусы»")
    public boolean isSaucesTabActive() {
        return wait.until(ExpectedConditions
                .attributeContains(saucesTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что активен раздел «Начинки»")
    public boolean isFillingsTabActive() {
        return wait.until(ExpectedConditions
                .attributeContains(fillingsTab, "class", "tab_tab_type_current"));
    }

    @Step("Проверить, что открыта главная страница")
    public boolean isConstructorDisplayed() {
        return waitVisible(constructorHeader).isDisplayed();
    }

    @Step("Проверить, что кнопка «Войти в аккаунт» отображается")
    public boolean isLoginButtonDisplayed() {
        return waitVisible(loginButton).isDisplayed();
    }

    @Step("Проверить, что пользователь авторизован")
    public boolean isUserAuthorized() {
        return waitVisible(personalAccountLink).isDisplayed();
    }
}