package com.worldpay.sdk.wpg.integration.wallet;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.wallet.ApplePayRequest;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ApplePayRequestTest extends BaseIntegrationTest
{

    @Test
    public void xmlTest() throws Exception
    {
        // Given
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper("email@email.com", "1.2.3.4",  browser);
        OrderDetails orderDetails = new OrderDetails("test", new Amount("EUR", 2L, 1234L));

        // When
        try
        {
            new ApplePayRequest(orderDetails, shopper, "ffff", "ffff", "ffff", "ffff", "ffff", "ffff")
                    .send(GATEWAY_CONTEXT);

            fail("Request should have thrown an exception");
        }
        catch (WpgErrorResponseException e)
        {
            assertThat(e.getGatewayErrorCode(), is(5L));
            assertTrue(e.getGatewayErrorMessage().matches("Couldn't decrypt Apple Pay transaction with merchant code: .+ for reason: Untrusted Certificate"));
        }
    }

}
