package Test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setup(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyFromSecondToFirstCard(){
        val dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.firstCardButton();
        val infoCard = DataHelper.getSecondCardNumber();
        String sum = "10000";
        transferPage.transferForm(sum, infoCard);
        assertEquals(firstCardBalance + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferMoneyFromFirstToSecondCard(){
        val dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "10000";
        transferPage.transferForm(sum, infoCard);
        assertEquals(secondCardBalance + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
        assertEquals(firstCardBalance - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
    }

    @Test
    public void shouldTransferMoneyAboveLimitFromSecondToFirstCard(){
        val dashboardPage = new DashboardPage();
        val transferPage = dashboardPage.firstCardButton();
        val infoCard = DataHelper.getSecondCardNumber();
        String sum = "11000";
        transferPage.transferForm(sum, infoCard);
        val dashboardPageWithError = new DashboardPage();
        dashboardPageWithError.getNotificationVisible();
    }


}

