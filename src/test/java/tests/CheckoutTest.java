package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CheckoutPage;
import pages.HomePage;

import static java.sql.DriverManager.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {
    @Test
    @DisplayName("Order as a guest")
    public void orderGuest() {
        HomePage homePage = new HomePage(context);
        homePage.addToCardQuick();

        CheckoutPage checkoutPage = new CheckoutPage(context);
        checkoutPage.fillGuestCheckoutForm();

       assertTrue(context.wait.until(ExpectedConditions.titleIs("Your order has been placed!")));
        //assertEquals("Your order has been placed!", getDriver(context).getTitle());
    }

    @Test
    @DisplayName("Order without agrees")
    public void orderWithoutAgrees() {
        HomePage homePage = new HomePage(context);
        homePage.addToCardQuick();

        CheckoutPage checkoutPage = new CheckoutPage(context);
        String alertMassageText = checkoutPage.makeOrderwithoutAgrres();

        assertEquals("Warning: You must agree to the Terms & Conditions!\n×",
               alertMassageText);
    }
}
