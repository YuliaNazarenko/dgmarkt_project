package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CheckoutPage;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    @DisplayName("Order as a guest")
    public void orderGuest() {
        HomePage homePage = new HomePage(context);
        homePage.addToCardByLink();

        CheckoutPage checkoutPage = new CheckoutPage(context);
        checkoutPage.fillGuestCheckoutForm();

        assertEquals("Your order has been placed!", context.driver.getTitle());
    }

    @Test
    @DisplayName("Order without agrees")
    public void orderWithoutAgrees() {
        HomePage homePage = new HomePage(context);
        homePage.addToCardByLink();

        CheckoutPage checkoutPage = new CheckoutPage(context);
        String alertMassageText = checkoutPage.makeOrderWithoutAgrees();

        assertEquals("Warning: You must agree to the Terms & Conditions!\n×",
                alertMassageText);
    }
}
