package com;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPageObject {

    public static final String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.CLASS_NAME, using = ("button_button_size_large__G21Vg"))
    public static SelenideElement enterAccount;

    @Step("Клик по кнопке Войти в аккаунт")
    public static void clickEnterAccount() {
        enterAccount.click();
    }

    @FindBy(how = How.LINK_TEXT, using = ("Личный Кабинет"))
    public static SelenideElement personalCabinet;

    @Step("Перейти в личный кабинет")
    public static void clickPersonalCabinet() {
        personalCabinet.click();
    }

    @FindBy(how = How.CLASS_NAME, using = ("button_button_size_large__G21Vg"))
    public static SelenideElement orderThat;

    @Step("Нажать на кнопку Оформить заказ")
    public static void clickOrderThat() {
        orderThat.click();
    }

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/section[1]/div[1]/div[1]"))
    public static SelenideElement constructorBoolki;

    @Step("Перейти в раздел конструктора Булки")
    public static void clickBoolki() {
        constructorBoolki.click();
    }

    @FindBy(how = How.CSS, using = ("div.tab_tab__1SPyG:nth-child(2)"))
    public static SelenideElement constructorSauses;

    @Step("Перейти в раздел конструктора Соусы")
    public static void clickSauses() {
        constructorSauses.click();
    }

    @FindBy(how = How.CSS, using = ("div.tab_tab__1SPyG:nth-child(3)"))
    public static SelenideElement constructorNachinki;

    @Step("Перейти в раздел конструктора Начинки")
    public static void clickNachinki() {
        constructorNachinki.click();
    }

}
