package ru.netology.page;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public PayByCard payByCard() {
        $(byXpath("//span[text()='Купить']")).click();
        return new PayByCard();
    }

    public BuyOnCredit buyOnCredit() {
        $(byXpath("//span[text()='Купить в кредит']")).click();
        return new BuyOnCredit();
    }
}
