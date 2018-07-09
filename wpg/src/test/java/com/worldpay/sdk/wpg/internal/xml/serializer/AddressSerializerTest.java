package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlEndpoint;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddressSerializerTest
{

    @Test
    public void decorateOrder_resetsBuilder()
    {
        // given
        XmlBuilder builder = new XmlBuilder(XmlEndpoint.PAYMENTS);
        builder.e("test");
        XmlBuildParams params = new XmlBuildParams(null, null, builder, null, false);

        Address address = new Address("address 1", "city", "post code", "country code");

        // when
        AddressSerializer.decorateOrder(params, address, address);

        // then
        assertThat(builder.getPath(), is("/#document/paymentService/test"));
    }

    @Test
    public void decorateCurrentElement_resetsBuilder()
    {
        // given
        XmlBuilder builder = new XmlBuilder(XmlEndpoint.PAYMENTS);
        builder.e("test");
        XmlBuildParams params = new XmlBuildParams(null, null, builder, null, false);

        Address address = new Address("address 1", "city", "post code", "country code");

        // when
        AddressSerializer.decorateCurrentElement(params, address);

        // then
        assertThat(builder.getPath(), is("/#document/paymentService/test"));
    }

}
