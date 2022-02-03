package YandexBrowserTest;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;


public class ConstructorYandexTest {

    @Before
    public void setUp() {

        Configuration.startMaximized = true;

    }

    @Before
    public void before() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");

        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void selectSauces() {

        MainPageObject.clickSauses();
        sleep(1000);

        assertThat("Выбраны не соусы", MainPageObject.constructorNachinki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Соусы не выбраны", MainPageObject.constructorSauses.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @Test
    @DisplayName("Переход к разделу булки")
    public void selectBoolki() {
        MainPageObject.clickNachinki();
        MainPageObject.clickSauses();
        MainPageObject.clickBoolki();
        sleep(1000);

        assertThat("Выбраны не булки", MainPageObject.constructorNachinki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Булки не выбраны", MainPageObject.constructorBoolki.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @Test
    @DisplayName("Переход к разделу начинки")
    public void selectNachinki() {

        MainPageObject.clickNachinki();
        sleep(1000);

        assertThat("Выбраны не начинки", MainPageObject.constructorBoolki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Начинки не выбраны", MainPageObject.constructorNachinki.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

}
