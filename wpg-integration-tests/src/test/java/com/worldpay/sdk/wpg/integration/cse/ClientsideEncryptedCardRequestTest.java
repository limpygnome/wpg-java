package com.worldpay.sdk.wpg.integration.cse;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.cse.ClientsideEncryptedCardRequest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO could be imrpoved by adding working encrypted payload test
 */
public class ClientsideEncryptedCardRequestTest extends BaseIntegrationTest
{

    @Test
    public void invalidData()
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount(Currency.EUR, 2L, 1234L));

        // When
        try
        {
            new ClientsideEncryptedCardRequest(orderDetails, "malformed encrypted data")
                    .send(GATEWAY_CONTEXT);
        }
        catch (WpgException e)
        {
            assertThat(e, is(CoreMatchers.instanceOf(WpgErrorResponseException.class)));

            WpgErrorResponseException ex = (WpgErrorResponseException) e;
            assertThat(ex.getGatewayErrorCode(), is(5L));
            assertThat(ex.getGatewayErrorMessage(), is("An internal CSE service error has occurred."));
        }
    }

}
