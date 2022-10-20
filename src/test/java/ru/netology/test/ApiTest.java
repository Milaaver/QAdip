package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.api.ApiHelper;
import ru.netology.data.DBaseQueries;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBaseQueries.*;
import static ru.netology.data.DBaseQueries.getCredit;

public class ApiTest {
    private ApiHelper api = new ApiHelper();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DBaseQueries.clearAllData();
    }

    @Test
    void shouldSuccessStatusSendValidFormSendApprovedCardForPay() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoValidHolder, pathForPay, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldSuccessStatusSendValidFormSendDeclinedCardForPay() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoHolderDeclinedCard, pathForPay, statusCode);
        assertEquals(DataHelper.getDeclinedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getDeclinedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldClientErrorStatusSendEmptyCardNumberForPay() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendAnyCardNumberForPay() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderAnyCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithPartNumberForPay() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolder, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInCardNumberForPay() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoMoreDigitsInCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardNumberWithoutDigitForPay() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoCardNumberWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthDoubleZeroForPay() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderDoubleZeroMonth, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthOverForPay() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolder13Month, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyMonthFieldForPay() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyMonthField, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitMonthForPay() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitMonth, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInMonthForPay() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInMonth, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthWithoutDigitForPay() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMonthWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendPastYearForPay() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderPastYear, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendFutureYearOverForPay() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderFutureYear, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyYearFieldForPay() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyYearField, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitYearForPay() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitYear, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInYearForPay() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInYear, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendYearWithoutDigitForPay() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderYearWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldSuccessStatusSendCardWithCurrentPeriodForPay() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoCardWithCurrentPeriod, pathForPay, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldClientErrorStatusSendCardWithCyrillicHolderForPay() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoCyrillicHolder, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithSymbolHolderForPay() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoSymbolHolder, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyHolderFieldForPay() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyHolderField, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyCvcFieldForPay() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyCvcField, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitCvcForPay() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitCvc, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInCvcForPay() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInCvc, pathForPay, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCvcWithoutDigitForPay() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderCvcWithoutDigit, pathForPay, statusCode);
    }
    // далее для кредитования

    @Test
    void shouldSuccessStatusSendApprovedCardForCredit() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoValidHolder, pathForCredit, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldSuccessStatusSendDeclinedCardForCredit() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoHolderDeclinedCard, pathForCredit, statusCode);
        assertEquals(DataHelper.getDeclinedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getDeclinedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldClientErrorStatusSendEmptyCardNumberForCredit() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendAnyCardNumberForCredit() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderAnyCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithPartNumberForCredit() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInCardNumberForCredit() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoMoreDigitsInCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardNumberWithoutDigitForCredit() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoCardNumberWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthDoubleZeroForCredit() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderDoubleZeroMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthOverForCredit() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolder13Month, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyMonthFieldForCredit() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyMonthField, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitMonthForCredit() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInMonthForCredit() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMonthWithoutDigitForCredit() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMonthWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendPastYearForCredit() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderPastYear, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendFutureYearOverForCredit() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderFutureYear, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyYearFieldForCredit() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyYearField, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitYearForCredit() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitYear, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInYearForCredit() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInYear, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendYearWithoutDigitForCredit() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderYearWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldSuccessStatusSendCardWithCurrentPeriodForCredit() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoCardWithCurrentPeriod, pathForCredit, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldClientErrorStatusSendCardWithCyrillicHolderForCredit() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoCyrillicHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithSymbolHolderForCredit() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoSymbolHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyHolderFieldForCredit() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyHolderField, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendEmptyCvcFieldForCredit() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoEmptyCvcField, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCardWithOneDigitCvcForCredit() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderOneDigitCvc, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendMoreDigitsInCvcForCredit() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMoreDigitsInCvc, pathForCredit, statusCode);
    }

    @Test
    void shouldClientErrorStatusSendCvcWithoutDigitForCredit() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderCvcWithoutDigit, pathForCredit, statusCode);
    }
}
