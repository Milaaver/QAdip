package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCard {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");;
    private SelenideElement holderField = $(byXpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvcField = $("[placeholder='999']");;
    private SelenideElement buttonCont = $$("button").find(text("Продолжить"));
    private SelenideElement messageSuccess = $(".icon_name_ok");
    private SelenideElement messageError = $(".icon_name_error");
    private SelenideElement subtitles = $(".input__sub");
    private ElementsCollection closer = $$(".notification__closer"); // закрытие сообщения не уникально

    public PayByCard() {
        $$("h3").find(text("Оплата по карте")).shouldBe(visible);
    }

    public void setUp(DataHelper.AuthInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        holderField.setValue(info.getHolder());
        cvcField.setValue(info.getCvc());
        buttonCont.click();
    }

    public void successMessage() {
        messageSuccess.shouldBe(visible, Duration.ofSeconds(16));
        closer.first().click();
        messageSuccess.shouldBe(hidden);
        messageError.shouldBe(hidden);
    }

    public void errorMessage() {
        messageError.shouldBe(visible, Duration.ofSeconds(16));
        closer.last().click();
        messageError.shouldBe(hidden);
        messageSuccess.shouldBe(hidden);
    }

    public void subWrongFormat() {
        subtitles.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void subNecessarilyField() {
        subtitles.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void setUpCardNumberField(String number, String digit) {
        cardNumberField.setValue(number + digit);
        cardNumberField.shouldHave(value(number));
    }

    public void setUpCardNumberFieldWithSymbol(String number, String digit) {
        cardNumberField.setValue(number + digit);
        cardNumberField.shouldHave(value(digit));
    }

    public void subWrongPeriod() {
        subtitles.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void setUpMonthField(String month, String digit) {
        monthField.setValue(month + digit);
        monthField.shouldHave(value(month));
    }

    public void setUpMonthFieldWithSymbol(String month, String digit) {
        monthField.setValue(month + digit);
        monthField.shouldHave(value(digit));
    }

    public void subExpired() {
        subtitles.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void setUpYearField(String year, String digit) {
        yearField.setValue(year + digit);
        yearField.shouldHave(value(year));
    }

    public void setUpYearFieldWithSymbol(String year, String digit) {
        yearField.setValue(year + digit);
        yearField.shouldHave(value(digit));
    }

    public void setUpCvcField(String cvc, String digit) {
        cvcField.setValue(cvc + digit);
        cvcField.shouldHave(value(cvc));
    }

    public void setUpCvcFieldWithSymbol(String cvc, String digit) {
        yearField.setValue(cvc + digit);
        yearField.shouldHave(value(digit));
    }

    public BuyOnCredit buyOnCredit() {
        $(byXpath("//span[text()='Купить в кредит']")).click();
        return new BuyOnCredit();
    }
}
