package ChromeTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;


public class PositiveRegistrationChromeTest {

    public String userName = RandomStringUtils.randomAlphabetic(10);
    public String userPassword = RandomStringUtils.randomAlphabetic(10);
    public String userMail = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".ru";

    @Before
    public void setUp() {

        Configuration.startMaximized = true;

    }

    @Test
    @DisplayName("Успешная регистрация")
    public void positiveRegistration() {
        LoginPageObject loginPageObject =
                open(LoginPageObject.URL, LoginPageObject.class);

        LoginPageObject.clickButtonRegistration();
        LoginPageObject.setRegEmail(userMail);
        LoginPageObject.setPassword(userPassword);
        LoginPageObject.setName(userName);
        LoginPageObject.clickRegistration();
        sleep(1000);
        assertThat("Регистрация не удалась, вы не на экране входа", LoginPageObject.zagolovok.getText(), hasToString("Вход"));
        assertThat("Регистрация не удалась, нет кнопки Войти", LoginPageObject.buttonEnterOrRegistration.getText(), hasToString("Войти"));

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

