package ChromeTest;

import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import com.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class ConstructorChromeTest {

    MainPageObject mainPageObject =
            open(MainPageObject.URL, MainPageObject.class);

    @Test
    @DisplayName("Переход к разделу соусы")
    public void selectSauces(){

        MainPageObject.constructorSauses.click();
        MainPageObject.constructorSauses
                .shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        MainPageObject.constructorBoolki
                .shouldNotHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Переход к разделу булки")
    public void selectBoolki(){
        MainPageObject.constructorNachinki.click();
        MainPageObject.constructorBoolki.click();
        MainPageObject.constructorSauses
                .shouldNotHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        MainPageObject.constructorBoolki
                .shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        closeWebDriver();
    }

    @Test
    @DisplayName("Переход к разделу начинки")
    public void selectNachinki(){

        MainPageObject.constructorNachinki.click();
        MainPageObject.constructorSauses
                .shouldNotHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        MainPageObject.constructorNachinki
                .shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
        closeWebDriver();
    }




}
