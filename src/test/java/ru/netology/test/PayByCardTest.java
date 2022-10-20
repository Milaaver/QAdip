package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBaseQueries;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBaseQueries.getOrder;
import static ru.netology.data.DBaseQueries.getPayment;

public class PayByCardTest {
    private MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpPage() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DBaseQueries.clearAllData();
    }

    @Test
    void shouldSuccessMessageSendValidFormUseApprovedCard() {
        var payByCard = mainPage.payByCard();
        var infoValidHolder = DataHelper.Registration.getValidUser();
        payByCard.setUp(infoValidHolder);
        payByCard.successMessage();
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldGetErrorMessageSendValidFormUseDeclinedCard() {
        var payByCard = mainPage.payByCard();
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        payByCard.setUp(infoHolderDeclinedCard);
        payByCard.errorMessage();
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getDeclinedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldGetSubtitleWrongFormatUseEmptyCardNumber() {
        var payByCard = mainPage.payByCard();
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        payByCard.setUp(infoEmptyCardNumber);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetErrorMessageUseAnyCardNumber() {
        var payByCard = mainPage.payByCard();
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        payByCard.setUp(infoHolderAnyCardNumber);
        payByCard.errorMessage();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithPartNumber() {
        var payByCard = mainPage.payByCard();
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        payByCard.setUp(infoHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldTakeOnly16DigitsInFieldUseMoreDigitsInCardNumber() {
        var payByCard = mainPage.payByCard();
        var cardNumber = DataHelper.getRandomCardNumber();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumberField(cardNumber, digit);
    }

    @Test
    void shouldNotTakeSymbolInFieldUseCardNumberWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var cardNumber = DataHelper.getSymbolString(16);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumberFieldWithSymbol(cardNumber, digit);
    }

    @Test
    void shouldGetSubtitleWrongPeriodUseMonthDoubleZero() {
        var payByCard = mainPage.payByCard();
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        payByCard.setUp(infoHolderDoubleZeroMonth);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldGetSubtitleWrongPeriodUseMonthOver() {
        var payByCard = mainPage.payByCard();
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        payByCard.setUp(infoHolder13Month);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseEmptyMonthField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        payByCard.setUp(infoEmptyMonthField);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithOneDigitMonth() {
        var payByCard = mainPage.payByCard();
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        payByCard.setUp(infoHolderOneDigitMonth);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldTakeOnly2DigitsInFieldUseMoreDigitsInMonth() {
        var payByCard = mainPage.payByCard();
        var month = DataHelper.getMonth();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpMonthField(month, digit);
    }

    @Test
    void shouldNotTakeSymbolInFieldUseMonthWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var month = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpMonthFieldWithSymbol(month, digit);
    }

    @Test
    void shouldGetSubtitleExpiredUsePastYear() {
        var payByCard = mainPage.payByCard();
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        payByCard.setUp(infoHolderPastYear);
        payByCard.subExpired();
    }

    @Test
    void shouldGetSubtitleWrongPeriodUseFutureYearOver() {
        var payByCard = mainPage.payByCard();
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        payByCard.setUp(infoHolderFutureYear);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseEmptyYearField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        payByCard.setUp(infoEmptyYearField);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithOneDigitYear() {
        var payByCard = mainPage.payByCard();
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        payByCard.setUp(infoHolderOneDigitYear);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldTakeOnly2DigitsInFieldUseMoreDigitsInYear() {
        var payByCard = mainPage.payByCard();
        var year = DataHelper.getYearFutureInPeriod();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpYearField(year, digit);
    }

    @Test
    void shouldNotTakeSymbolInFieldUseYearWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var year = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpYearFieldWithSymbol(year, digit);
    }

    @Test
    void shouldSuccessMessageUseCardWithCurrentPeriod() {
        var payByCard = mainPage.payByCard();
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        payByCard.setUp(infoCardWithCurrentPeriod);
        payByCard.successMessage();
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithCyrillicHolder() {
        var payByCard = mainPage.payByCard();
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        payByCard.setUp(infoCyrillicHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithSymbolHolder() {
        var payByCard = mainPage.payByCard();
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        payByCard.setUp(infoSymbolHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetSubtitleNecessarilyFieldUseEmptyHolderField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        payByCard.setUp(infoEmptyHolderField);
        payByCard.subNecessarilyField();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseEmptyCvcField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        payByCard.setUp(infoEmptyCvcField);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldGetSubtitleWrongFormatUseCardWithOneDigitCvc() {
        var payByCard = mainPage.payByCard();
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        payByCard.setUp(infoHolderOneDigitCvc);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldTakeOnly3DigitsInFieldUseMoreDigitsInCvc() {
        var payByCard = mainPage.payByCard();
        var cvc = DataHelper.getCVC();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCvcField(cvc, digit);
    }

    @Test
    void shouldNotTakeSymbolInFieldUseCvcWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var cvc = DataHelper.getSymbolString(3);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCvcFieldWithSymbol(cvc, digit);
    }

    @Test
    void shouldSwitchOnPageByCredit() {
        var payByCard = mainPage.payByCard();
        payByCard.buyOnCredit();
    }
}
