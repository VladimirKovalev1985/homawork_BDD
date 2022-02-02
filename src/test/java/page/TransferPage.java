package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import data.DataHelper;

public class TransferPage {

    private final SelenideElement heading = $(withText("Пополнение карты"));
    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement from = $("[data-test-id='from'] input");
    private final SelenideElement button = $("[data-test-id='action-transfer']");
    private final SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private final SelenideElement error = $("[data-test-id='error-notification']");

    public TransferPage(){
        heading.shouldBe(visible);
    }

    public DashboardPage transferForm(String sum, DataHelper.CardNumber cardNumber){
        amount.setValue(sum);
        from.setValue(String.valueOf(cardNumber));
        button.click();
        return new DashboardPage();
    }

    }

