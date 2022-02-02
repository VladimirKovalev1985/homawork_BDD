package page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement firstCard = $$(".list__item").first();
    private final SelenideElement secondCard = $$(".list__item").last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement firstButton = $$("[data-test-id='action-deposit']").first();
    private final SelenideElement secondButton = $$("[data-test-id='action-deposit']").last();
    private final SelenideElement reload = $("[data-test-id='action-reload']");
    private SelenideElement notification = $("[data-test-id=error-notification]").$(withText("Ошибка"));

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage firstCardButton() {
        firstButton.click();
        return new TransferPage();
    }

    public TransferPage secondCardButton() {
        secondButton.click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = firstCard.text();
        return extractBalanceCard1(text);
    }

    private int extractBalanceCard1(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = secondCard.text();
        return extractBalanceCard2(text);
    }

    private int extractBalanceCard2(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public SelenideElement getNotificationVisible() {
        return notification.shouldBe(visible);


    }
}

