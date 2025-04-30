package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CheckoutPage;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {
    @Test
    @DisplayName("Order as a guest")
    public void orderGuest() throws InterruptedException {
        HomePage homePage = new HomePage(context);
        homePage.addToCardQuick();

        assertEquals("Your Order Has Been Placed!\nShopping Cart Checkout Success", new CheckoutPage(context).fillGuestCheckoutForm());
    }
}
