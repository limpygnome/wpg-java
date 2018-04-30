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
    private String countryCode;
    private String telephoneNumber;

    public Address(String address1, String city, String postalCode, CountryCode countryCode)
    {
        this.address1 = address1;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode.ISO3166_1_ALPHA_2_COUNTRY_CODE;
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
        this.countryCode = countryCode.ISO3166_1_ALPHA_2_COUNTRY_CODE;
        this.telephoneNumber = telephoneNumber;
    }

    public Address(String firstName, String lastName, String address1, String address2, String address3, String postalCode, String city, String state, String countryCode, String telephoneNumber)
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

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode)
    {
        this.countryCode = countryCode.ISO3166_1_ALPHA_2_COUNTRY_CODE;
    }

    public void setCountryCode(String countryCode)
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (firstName != null ? !firstName.equals(address.firstName) : address.firstName != null) return false;
        if (lastName != null ? !lastName.equals(address.lastName) : address.lastName != null) return false;
        if (address1 != null ? !address1.equals(address.address1) : address.address1 != null) return false;
        if (address2 != null ? !address2.equals(address.address2) : address.address2 != null) return false;
        if (address3 != null ? !address3.equals(address.address3) : address.address3 != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (countryCode != null ? !countryCode.equals(address.countryCode) : address.countryCode != null) return false;
        return telephoneNumber != null ? telephoneNumber.equals(address.telephoneNumber) : address.telephoneNumber == null;
    }

    @Override
    public int hashCode()
    {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

}
