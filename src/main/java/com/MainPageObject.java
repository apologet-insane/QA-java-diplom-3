package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPageObject {

    public static final String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/section[2]/div/button"))
    public static SelenideElement enterAccount;

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/header/nav/a/p"))
    public static SelenideElement personalCabinet;

    @FindBy(how = How.CLASS_NAME, using = ("button_button_size_large__G21Vg"))
    public static SelenideElement orderThat;

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/section[1]/div[1]/div[1]"))
    public static SelenideElement constructorBoolki;

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/section[1]/div[1]/div[2]"))
    public static SelenideElement constructorSauses;

    @FindBy(how = How.XPATH, using = ("/html/body/div/div/main/section[1]/div[1]/div[3]"))
    public static SelenideElement constructorNachinki;





}
