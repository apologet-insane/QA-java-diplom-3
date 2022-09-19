package ChromeTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;


public class AuthorizationChromeTest {

    public static String userName = RandomStringUtils.randomAlphabetic(10);
    public static String userPassword = RandomStringUtils.randomAlphabetic(10);
    public static String userMail = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".ru";

    @Before
    public void setUp() {

        Configuration.startMaximized = true;
    }

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

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт на главной странице")
    public void enterWithButtonOnMainPageTest() {
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickEnterAccount();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        assertThat("Вход не выполнен", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));

    }

    @Test
    @DisplayName("Вход через кнопку Личный профиль на главной странице")
    public void enterWithProfileOnMainPageTest() {
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickPersonalCabinet();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        assertThat("Вход не выполнен", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterWithButtonOnRegPageTest() {
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickPersonalCabinet();
        LoginPageObject.clickButtonRegistration();
        LoginPageObject.clickFromRegistrationToLoginPage();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        assertThat("Вход не выполнен", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановление пароля")
    public void enterWithButtonOnRecoveryPasswordPageTest() {
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickPersonalCabinet();
        LoginPageObject.clickButtonResetPassword();
        LoginPageObject.clickFromReRecoveryPasswordToLoginPage();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        assertThat("Вход не выполнен", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));

    }

    @After
    public void tearDown() {

        String registerRequestBody = "{\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";

        String token = given()
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

    @After
    public void closeWebDriver() {

        Selenide.closeWebDriver();
    }

}
