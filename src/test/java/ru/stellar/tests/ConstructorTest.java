package ru.stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellar.base.BaseTest;
import ru.stellar.pages.MainPage;

@Epic("Stellar Burgers")
@Feature("Конструктор")
public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверяем, что при клике на таб «Булки» он становится активным")
    public void switchToBunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue("Таб «Булки» должен быть активным",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверяем, что при клике на таб «Соусы» он становится активным")
    public void switchToSaucesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();

        Assert.assertTrue("Таб «Соусы» должен быть активным",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверяем, что при клике на таб «Начинки» он становится активным")
    public void switchToFillingsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();

        Assert.assertTrue("Таб «Начинки» должен быть активным",
                mainPage.isFillingsTabActive());
    }
}
