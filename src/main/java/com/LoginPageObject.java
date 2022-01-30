package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageObject {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy (how = How.LINK_TEXT, using = "Конструктор")
    public static SelenideElement konstruktor;

    @FindBy (how = How.LINK_TEXT, using = "Лента Заказов")
    public static SelenideElement lentaZakazov;

    @FindBy (how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    public static SelenideElement logotip;

    @FindBy (how = How.LINK_TEXT, using = "Личный Кабинет")
    public static SelenideElement cabinet;

    @FindBy (how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[2]/div/div/input")
    public static SelenideElement fieldRegEmail;

    @FindBy (how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[1]/div/div/input")
    public static SelenideElement fieldLogEmail;

    @FindBy (how = How.NAME, using = "Пароль")
    public static SelenideElement fieldPassword;

    @FindBy (how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[1]/div/div/input")
    public static SelenideElement fieldName;

    @FindBy (how = How.XPATH, using = "/html/body/div/div/main/div/form/button")
    public static SelenideElement buttonEnterOrRegistration;

    @FindBy (how = How.LINK_TEXT, using = "Зарегистрироваться")
    public static SelenideElement buttonRegistration;

    @FindBy (how = How.LINK_TEXT, using = "Восстановить пароль")
    public static SelenideElement buttonResetPassword;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/h2")
    public static SelenideElement zagolovok;

    @FindBy (how = How.CLASS_NAME, using = "input__error")
    public static SelenideElement passwordError;

    @FindBy (how = How.CSS, using = (".Auth_link__1fOlj"))
    public static SelenideElement fromRegistrationToLoginPage;

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/div/div/p/a"))
    public static SelenideElement fromRecoveryPasswordToLoginPage;


}
