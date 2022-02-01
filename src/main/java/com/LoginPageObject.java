package com;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageObject {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[2]/div/div/input")
    public static SelenideElement fieldRegEmail;

    @Step("Ввод почты для регистрации")
    public static void setRegEmail(String email) {
        fieldRegEmail.setValue(email);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[1]/div/div/input")
    public static SelenideElement fieldLogEmailOrRegName;

    @Step("Ввод почты")
    public static void setLogEmail(String emailOrName) {
        fieldLogEmailOrRegName.setValue(emailOrName);
    }

    @Step("Ввод имени")
    public static void setName(String emailOrName) {
        fieldLogEmailOrRegName.setValue(emailOrName);
    }

    @FindBy(how = How.NAME, using = "Пароль")
    public static SelenideElement fieldPassword;

    @Step("Ввод пароля")
    public static void setPassword(String password) {
        fieldPassword.setValue(password);
    }

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    public static SelenideElement buttonEnterOrRegistration;

    @Step("Нажать Войти")
    public static void clickEnter() {
        buttonEnterOrRegistration.click();
    }

    @Step("Нажать Зарегистрироваться")
    public static void clickRegistration() {
        buttonEnterOrRegistration.click();
    }

    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    public static SelenideElement buttonRegistration;

    @Step("Нажать Зарегистрироваться, чтобы перейти в форму регистрации")
    public static void clickButtonRegistration() {
        buttonRegistration.click();
    }

    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    public static SelenideElement buttonResetPassword;

    @Step("Переход в форму восстановления пароля")
    public static void clickButtonResetPassword() {
        buttonResetPassword.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/h2")
    public static SelenideElement zagolovok;

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    public static SelenideElement passwordError;

    @FindBy(how = How.CSS, using = (".Auth_link__1fOlj"))
    public static SelenideElement fromRegistrationToLoginPage;

    @Step("Переход с формы регистрации на страницу входа")
    public static void clickFromRegistrationToLoginPage() {
        fromRegistrationToLoginPage.click();
    }

    @FindBy(how = How.CLASS_NAME, using = ("Auth_link__1fOlj"))
    public static SelenideElement fromRecoveryPasswordToLoginPage;

    @Step("Переход с формы восстановления пароля на страницу входа")
    public static void clickFromReRecoveryPasswordToLoginPage() {
        fromRecoveryPasswordToLoginPage.click();
    }

}
