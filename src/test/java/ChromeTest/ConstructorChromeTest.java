package ChromeTest;


import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ConstructorChromeTest {


    @Before
    public void setUp() {

        Configuration.startMaximized = true;

    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void selectSauces() {
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickSauses();
        sleep(1000);

        assertThat("Выбраны не соусы", MainPageObject.constructorNachinki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Соусы не выбраны", MainPageObject.constructorSauses.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @Test
    @DisplayName("Переход к разделу булки")
    public void selectBoolki() {
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickNachinki();
        MainPageObject.clickSauses();
        sleep(1000);
        MainPageObject.clickBoolki();
        sleep(1000);

        assertThat("Выбраны не булки", MainPageObject.constructorNachinki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Булки не выбраны", MainPageObject.constructorBoolki.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @Test
    @DisplayName("Переход к разделу начинки")
    public void selectNachinki() {
        MainPageObject mainPageObject =
                open(MainPageObject.URL, MainPageObject.class);

        MainPageObject.clickNachinki();
        sleep(1000);

        assertThat("Выбраны не начинки", MainPageObject.constructorBoolki.toString(), not(containsString("tab_tab_type_current__2BEPc")));
        assertThat("Начинки не выбраны", MainPageObject.constructorNachinki.toString(), anyOf(containsString("tab_tab_type_current__2BEPc")));

    }

    @After
    public void tearDown()
    {
        closeWebDriver();
    }


}
