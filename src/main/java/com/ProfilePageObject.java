package com;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePageObject {

    public static final String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.LINK_TEXT, using = "Конструктор")
    public static SelenideElement konstruktor;

    @Step("Нажать на Конструктор")
    public static void clickKonstruktor() {
        konstruktor.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/nav/p")
    public static SelenideElement textWhatYouCanInProfile;

    @FindBy(how = How.CLASS_NAME, using = ("AppHeader_header__logo__2D0X2"))
    public static SelenideElement logo;

    @Step("Нажать на логотип")
    public static void clickLogo() {
        logo.click();
    }

    @FindBy(how = How.CLASS_NAME, using = ("Account_button__14Yp3"))
    public static SelenideElement exitAccount;

    @Step("Нажать на выход из аккаунта")
    public static void clickExitAccount() {
        exitAccount.click();
    }

}
