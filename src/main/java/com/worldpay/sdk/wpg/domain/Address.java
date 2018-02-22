package com.worldpay.sdk.wpg.domain;

public class Address
{
    private String firstName;
    private String lastName;

    private String address1;
    private String address2;
    private String address3;

    private String postalCode;
    private String city;
    private String state;
    private CountryCode countryCode;
    private String telephoneNumber;

    public Address(String address1, String city, String postalCode, CountryCode countryCode)
    {
        this.address1 = address1;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public Address(String firstName, String lastName, String address1, String address2, String address3, String postalCode, String city, String state, CountryCode countryCode, String telephoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.countryCode = countryCode;
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getAddress3()
    {
        return address3;
    }

    public void setAddress3(String address3)
    {
        this.address3 = address3;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public CountryCode getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

}
