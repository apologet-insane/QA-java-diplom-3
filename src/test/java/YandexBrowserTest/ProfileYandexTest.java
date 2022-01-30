package YandexBrowserTest;

import com.codeborne.selenide.Condition;
import com.ProfilePageObject;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


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
    public void before(){

        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);

        ProfilePageObject profilePageObject =
                open(ProfilePageObject.URL, ProfilePageObject.class);

        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);
    }


    @Test
    @DisplayName("Вход в Личный кабинет с главной страницы")
    public void enterProfileFromMainPage(){
        MainPageObject.enterAccount.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.personalCabinet.click();
        ProfilePageObject.textWhatYouCanInProfile.shouldHave(Condition.exactText("В этом разделе вы можете изменить свои персональные данные"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через Конструктор")
    public void fromProfileToMainPageWithConstructor(){
        MainPageObject.enterAccount.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.personalCabinet.click();
        ProfilePageObject.konstruktor.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу через логотип")
    public void fromProfileToMainPageWithLogo(){
        MainPageObject.enterAccount.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.personalCabinet.click();
        ProfilePageObject.logo.click();
        MainPageObject.orderThat.shouldHave(Condition.exactText("Оформить заказ"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitFromAccount(){
        MainPageObject.enterAccount.click();
        LoginPageObject.fieldLogEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(userPassword);
        LoginPageObject.buttonEnterOrRegistration.click();
        MainPageObject.personalCabinet.click();
        ProfilePageObject.exitAccount.click();
        LoginPageObject.buttonEnterOrRegistration.shouldHave(Condition.exactText("Войти"));
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
