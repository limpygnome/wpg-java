package com.worldpay.sdk.wpg.xml.decorator;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class AddressDecorator
{
    public static void decorate(XmlBuildParams params, Address billingAddress, Address shippingAddress)
    {
        XmlBuilder builder = params.xmlBuilder();
        builder.e("submit")
                .e("order");

        decorateAddress("shippingAddress", builder, shippingAddress);
        decorateAddress("billingAddress", builder, billingAddress);

        // Reset
        builder.reset();
    }

    private static void decorateAddress(String elementName, XmlBuilder builder, Address address)
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
            builder.e("firstName").cdata(address.getFirstName()).up();
        }
        if (address.getLastName() != null)
        {
            builder.e("lastName").cdata(address.getLastName()).up();
        }
        builder.e("address1").cdata(address.getAddress1()).up();
        if (address.getAddress2() != null)
        {
            builder.e("address2").cdata(address.getAddress2()).up();
        }
        if (address.getAddress3() != null)
        {
            builder.e("address3").cdata(address.getAddress3()).up();
        }
        builder.e("postalCode").cdata(address.getPostalCode()).up();
        if (address.getCity() != null)
        {
            builder.e("city").cdata(address.getCity()).up();
        }
        if (address.getState() != null)
        {
            builder.e("state").cdata(address.getState()).up();
        }
        builder.e("countryCode").cdata(address.getCountryCode().ISO3166_1_ALPHA_2_COUNTRY_CODE).up();
        if (address.getTelephoneNumber() != null)
        {
            builder.e("telephoneNumber").cdata(address.getTelephoneNumber()).up();
        }

        builder.up()
                .up();
    }

}
