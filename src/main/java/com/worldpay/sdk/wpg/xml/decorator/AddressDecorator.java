package com.worldpay.sdk.wpg.xml.decorator;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;

public class AddressDecorator
{
    public static void decorate(XmlBuildParams params, Address billingAddress, Address shippingAddress)
    {
        XMLBuilder2 builder = params.xmlBuilder2();
        builder.e("submit")
                .e("order");

        decorateAddress("shippingAddress", builder, shippingAddress);
        decorateAddress("billingAddress", builder, billingAddress);

        // Reset
        builder.up(2);
    }

    private static void decorateAddress(String elementName, XMLBuilder2 builder, Address address)
    {
        builder.e(elementName)
                .e("address");

        if (address.getAddress1() == null)
        {
            throw new IllegalArgumentException("Address 1 is mandatory for " + elementName);
        }
        else if (address.getPostalCode() == null)
        {
            throw new IllegalArgumentException("Postal code is mandatory for " + elementName);
        }
        else if (address.getCountryCode() == null)
        {
            throw new IllegalArgumentException("Country code is mandatory for " + elementName);
        }

        if (address.getFirstName() != null)
        {
            builder.e("firstName").cdata(address.getFirstName()).up(1);
        }
        if (address.getLastName() != null)
        {
            builder.e("lastName").cdata(address.getLastName()).up(1);
        }
        builder.e("address1").cdata(address.getAddress1()).up(1);
        if (address.getAddress2() != null)
        {
            builder.e("address2").cdata(address.getAddress2()).up(1);
        }
        if (address.getAddress3() != null)
        {
            builder.e("address3").cdata(address.getAddress3()).up(1);
        }
        builder.e("postalCode").cdata(address.getPostalCode()).up(1);
        if (address.getCity() != null)
        {
            builder.e("city").cdata(address.getCity()).up(1);
        }
        if (address.getState() != null)
        {
            builder.e("state").cdata(address.getState()).up(1);
        }
        builder.e("countryCode").cdata(address.getCountryCode().ISO3166_1_ALPHA_2_COUNTRY_CODE).up(1);
        if (address.getTelephoneNumber() != null)
        {
            builder.e("telephoneNumber").cdata(address.getTelephoneNumber()).up(1);
        }

        // Reset
        builder.up(2);
    }

}
