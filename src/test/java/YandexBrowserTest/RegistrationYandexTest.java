package YandexBrowserTest;

import com.LoginPageObject;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;


public class RegistrationYandexTest {
        public String userName = RandomStringUtils.randomAlphabetic(10);
        public String userPassword = RandomStringUtils.randomAlphabetic(10);
        public String shortUserPassword = RandomStringUtils.randomAlphabetic(5);
        public String userMail = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".ru";


        @Before
        public void before(){

       System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
            LoginPageObject loginPageObject = open(LoginPageObject.URL, LoginPageObject.class);
        }


        @Test
        @DisplayName("Успешная регистрация")
        public void positiveRegistration(){

            LoginPageObject.buttonRegistration.click();
            LoginPageObject.fieldRegEmail.setValue(userMail);
            LoginPageObject.fieldPassword.setValue(userPassword);
            LoginPageObject.fieldName.setValue(userName);
            LoginPageObject.buttonEnterOrRegistration.click();
            LoginPageObject.zagolovok.shouldHave(Condition.exactText("Вход"));
            LoginPageObject.buttonEnterOrRegistration.shouldHave(Condition.exactText("Войти"));
            closeWebDriver();

        }

    @Step
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

        @Test
        @DisplayName("Регистрация с паролем короче 6 символов")
        public void registrationWithShortPassword(){

            LoginPageObject.buttonRegistration.click();
            LoginPageObject.fieldRegEmail.setValue(userMail);
            LoginPageObject.fieldPassword.setValue(shortUserPassword);
            LoginPageObject.fieldName.setValue(userName);
            LoginPageObject.buttonEnterOrRegistration.click();
            LoginPageObject.zagolovok.shouldHave(Condition.exactText("Регистрация"));
            LoginPageObject.passwordError.shouldBe(Condition.visible);
            LoginPageObject.passwordError.shouldHave(Condition.exactText("Некорректный пароль"));
            closeWebDriver();

        }


}

