package YandexBrowserTest;

import com.ProfilePageObject;
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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasToString;


public class ProfileYandexTest {

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
    public void before() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);

        ProfilePageObject profilePageObject =
                open(ProfilePageObject.URL, ProfilePageObject.class);

        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);
    }


    @Test
    @DisplayName("Вход в Личный кабинет с главной страницы")
    public void enterProfileFromMainPage() {

        MainPageObject.clickEnterAccount();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        MainPageObject.clickPersonalCabinet();
        assertThat("Вход в личный кабинет не удался", ProfilePageObject.textWhatYouCanInProfile.getText(), anyOf(containsString("В этом разделе вы можете изменить свои персональные данные")));

    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через Конструктор")
    public void fromProfileToMainPageWithConstructor() {

        MainPageObject.clickEnterAccount();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        MainPageObject.clickPersonalCabinet();
        ProfilePageObject.clickKonstruktor();
        assertThat("Не найдена кнопка Конструктор в Личном кабинете", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через логотип")
    public void fromProfileToMainPageWithLogo() {

        MainPageObject.clickEnterAccount();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        MainPageObject.clickPersonalCabinet();
        ProfilePageObject.clickLogo();
        assertThat("Логотип не ведёт на главный экран", MainPageObject.orderThat.getText(), hasToString("Оформить заказ"));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitFromAccount() {

        MainPageObject.clickEnterAccount();
        LoginPageObject.setLogEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.clickEnter();
        MainPageObject.clickPersonalCabinet();
        ProfilePageObject.clickExitAccount();
        sleep(1000);
        assertThat("Выход не ведёт на страницу входа", LoginPageObject.buttonEnterOrRegistration.getText(), hasToString("Войти"));
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
