package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import static org.junit.Assert.*;
import org.junit.Test;

import java.net.URL;

public class ThreeDsRedirectBuilderTest
{

    @Test(expected = IllegalStateException.class)
    public void build_noTermUrl_illegalStateException()
    {
        // given
        ThreeDsDetails threeDsDetails = new ThreeDsDetails("issuer URL", "pa req");

        // when
        ThreeDsRedirectBuilder builder = new ThreeDsRedirectBuilder(threeDsDetails);
        builder.build();
    }

    @Test
    public void build_asExpected()
    {
        // given
        ThreeDsDetails threeDsDetails = new ThreeDsDetails("issuer URL", "pa req");
        String termUrl = "term url";

        // when
        ThreeDsRedirectBuilder builder = new ThreeDsRedirectBuilder(threeDsDetails);
        builder.termUrl(termUrl);

        String page = builder.build();

        // then
        String expected = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>3ds authentication redirect</title></head><body onload=\"auth_onloadEvent()\"><form name=\"form3ds\" method=\"post\" action=\"issuer URL\"><input type=\"hidden\" name=\"PaReq\" value=\"pa req\" /><input type=\"hidden\" name=\"TermUrl\" value=\"term url\" /><input type=\"hidden\" name=\"MD\" value=\"null\" /><input type=\"submit\" value=\"Continue\" /></form><script type=\"text/javascript\">function auth_onloadEvent() {document.form3ds.submit();}</script></body></html>";
        assertEquals("Page not built as expected", expected, page);
    }

    @Test
    public void build_withMerchantData()
    {
        // given
        ThreeDsDetails threeDsDetails = new ThreeDsDetails("issuer URL", "pa req");
        String termUrl = "term url";

        // when
        ThreeDsRedirectBuilder builder = new ThreeDsRedirectBuilder(threeDsDetails);
        builder.termUrl(termUrl);
        builder.merchantData("merchant data");

        String page = builder.build();

        // then
        String expected = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>3ds authentication redirect</title></head><body onload=\"auth_onloadEvent()\"><form name=\"form3ds\" method=\"post\" action=\"issuer URL\"><input type=\"hidden\" name=\"PaReq\" value=\"pa req\" /><input type=\"hidden\" name=\"TermUrl\" value=\"term url\" /><input type=\"hidden\" name=\"MD\" value=\"merchant data\" /><input type=\"submit\" value=\"Continue\" /></form><script type=\"text/javascript\">function auth_onloadEvent() {document.form3ds.submit();}</script></body></html>";
        assertEquals("Page not built as expected", expected, page);
    }

    @Test
    public void build_termUrl() throws Exception
    {
        // given
        ThreeDsDetails threeDsDetails = new ThreeDsDetails("issuer URL", "pa req");

        // when
        ThreeDsRedirectBuilder builder = new ThreeDsRedirectBuilder(threeDsDetails);
        builder.termUrl(new URL("https://termurl"));

        String page = builder.build();

        // then
        String expected = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>3ds authentication redirect</title></head><body onload=\"auth_onloadEvent()\"><form name=\"form3ds\" method=\"post\" action=\"issuer URL\"><input type=\"hidden\" name=\"PaReq\" value=\"pa req\" /><input type=\"hidden\" name=\"TermUrl\" value=\"https://termurl\" /><input type=\"hidden\" name=\"MD\" value=\"null\" /><input type=\"submit\" value=\"Continue\" /></form><script type=\"text/javascript\">function auth_onloadEvent() {document.form3ds.submit();}</script></body></html>";
        assertEquals("Page not built as expected", expected, page);
    }

}
