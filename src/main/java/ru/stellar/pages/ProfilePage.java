package ru.stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//a[text()='Профиль']")
    private WebElement profileLink;

    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement logoutButton;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что открыт личный кабинет")
    public boolean isOpened() {
        return waitVisible(profileLink).isDisplayed();
    }

    @Step("Получить значение поля «Имя»")
    public String getNameValue() {
        return waitVisible(nameInput).getAttribute("value");
    }
}
