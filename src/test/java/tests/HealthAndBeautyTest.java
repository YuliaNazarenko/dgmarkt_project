package tests;

import org.junit.jupiter.api.Test;
import pages.HealthAndBeautyPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthAndBeautyTest extends BaseTest {

    @Test
    public void addToCard() {
        HealthAndBeautyPage healthAndBeautyPage = new HealthAndBeautyPage(context);

        assertEquals("Success: You have added " +
                        "Nicky Clarke NSS111 Classic Ceramic & Tourmaline Straightner to your shopping cart!\n×",
                healthAndBeautyPage.chooseByCategory());
    }
}
