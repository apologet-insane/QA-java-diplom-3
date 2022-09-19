package YandexBrowserTest;

import com.LoginPageObject;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;


public class NegativeRegistrationYandexTest {

    public String userName = RandomStringUtils.randomAlphabetic(10);
    public String shortUserPassword = RandomStringUtils.randomAlphabetic(5);
    public String userMail = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".ru";


    @Before
    public void before() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        LoginPageObject loginPageObject = open(LoginPageObject.URL, LoginPageObject.class);
    }

    @Before
    public void setUp() {

        Configuration.startMaximized = true;

    }

    @Test
    @DisplayName("Регистрация с паролем короче 6 символов")
    public void registrationWithShortPassword() {

        LoginPageObject.buttonRegistration.click();
        LoginPageObject.fieldRegEmail.setValue(userMail);
        LoginPageObject.fieldPassword.setValue(shortUserPassword);
        LoginPageObject.setLogEmail(userName);
        LoginPageObject.buttonEnterOrRegistration.click();
        LoginPageObject.zagolovok.shouldHave(Condition.exactText("Регистрация"));
        LoginPageObject.passwordError.shouldBe(Condition.visible);
        assertThat("Уведомление о коротком пароле не появилось", LoginPageObject.passwordError.getText(), hasToString("Некорректный пароль"));

    }

    @After
    public void closeWebDriver() {

        Selenide.closeWebDriver();
    }

}

