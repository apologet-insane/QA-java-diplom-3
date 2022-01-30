package YandexBrowserTest;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class AuthorizationYandexTest {

    public static String userName = RandomStringUtils.randomAlphabetic(10);
    public static String userPassword = RandomStringUtils.randomAlphabetic(10);
    public static String userMail = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".ru";


    @Before
    public void createTestUser() {
        String registerRequestBody = "{\"name\":\"" + userName + "\","
                + "\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";
        RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
    }

    @Before
    public void before(){

        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);
    }



    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт на главной странице")
    public void enterWithButtonOnMainPage(){
        MainPageObject.enterAccount.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();

    }

    @Test
    @DisplayName("Вход через кнопку Личный профиль на главной странице")
    public void enterWithProfileOnMainPage(){
        MainPageObject.personalCabinet.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterWithButtonOnRegPage(){
        MainPageObject.personalCabinet.click();
        LoginPageObject.buttonRegistration.click();
        LoginPageObject.fromRegistrationToLoginPage.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterWithButtonOnRecoveryPasswordPage(){
        MainPageObject.personalCabinet.click();
        LoginPageObject.buttonResetPassword.click();
        LoginPageObject.fromRecoveryPasswordToLoginPage.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();

    }

    @After
    public void tearDown(){
        String registerRequestBody = "{\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";

        String token =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then().assertThat().statusCode(200).and().extract().path("accessToken");

        given()
                .auth().oauth2(token.substring(7))
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");

    }

}
