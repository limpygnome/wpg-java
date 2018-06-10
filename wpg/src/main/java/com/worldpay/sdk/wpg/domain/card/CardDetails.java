package com.worldpay.sdk.wpg.domain.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.internal.logging.CardObfuscator;

public class CardDetails
{
    private String cardNumber;
    private Long expiryMonth;
    private Long expiryYear;
    private String cardHolderName;
    private String cvc;
    private Address cardHolderAddress;
    private String encryptedCardNumber;

    public CardDetails(String cardNumber, Long expiryMonth, Long expiryYear, String cardHolderName)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
    }

    public CardDetails(String cardNumber, Long expiryMonth, Long expiryYear, String cardHolderName, String cvc)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
    }

    public CardDetails(String cardNumber, Long expiryMonth, Long expiryYear, String cardHolderName, String cvc, Address cardHolderAddress)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
        this.cardHolderAddress = cardHolderAddress;
    }

    public CardDetails(String cardNumber, Long expiryMonth, Long expiryYear, String cardHolderName, String cvc, Address cardHolderAddress, String encryptedCardNumber)
    {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardHolderName = cardHolderName;
        this.cvc = cvc;
        this.cardHolderAddress = cardHolderAddress;
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public Long getExpiryMonth()
    {
        return expiryMonth;
    }

    public void setExpiryMonth(Long expiryMonth)
    {
        this.expiryMonth = expiryMonth;
    }

    public Long getExpiryYear()
    {
        return expiryYear;
    }

    public void setExpiryYear(Long expiryYear)
    {
        this.expiryYear = expiryYear;
    }

    public String getCardHolderName()
    {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName)
    {
        this.cardHolderName = cardHolderName;
    }

    public String getCvc()
    {
        return cvc;
    }

    public void setCvc(String cvc)
    {
        this.cvc = cvc;
    }

    public Address getCardHolderAddress()
    {
        return cardHolderAddress;
    }

    public void setCardHolderAddress(Address cardHolderAddress)
    {
        this.cardHolderAddress = cardHolderAddress;
    }

    public String getEncryptedCardNumber()
    {
        return encryptedCardNumber;
    }

    public void setEncryptedCardNumber(String encryptedCardNumber)
    {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDetails that = (CardDetails) o;

        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (expiryMonth != null ? !expiryMonth.equals(that.expiryMonth) : that.expiryMonth != null) return false;
        if (expiryYear != null ? !expiryYear.equals(that.expiryYear) : that.expiryYear != null) return false;
        if (cardHolderName != null ? !cardHolderName.equals(that.cardHolderName) : that.cardHolderName != null)
            return false;
        if (cvc != null ? !cvc.equals(that.cvc) : that.cvc != null) return false;
        if (cardHolderAddress != null ? !cardHolderAddress.equals(that.cardHolderAddress) : that.cardHolderAddress != null)
            return false;
        return encryptedCardNumber != null ? encryptedCardNumber.equals(that.encryptedCardNumber) : that.encryptedCardNumber == null;
    }

    @Override
    public int hashCode()
    {
        int result = cardNumber != null ? cardNumber.hashCode() : 0;
        result = 31 * result + (expiryMonth != null ? expiryMonth.hashCode() : 0);
        result = 31 * result + (expiryYear != null ? expiryYear.hashCode() : 0);
        result = 31 * result + (cardHolderName != null ? cardHolderName.hashCode() : 0);
        result = 31 * result + (cvc != null ? cvc.hashCode() : 0);
        result = 31 * result + (cardHolderAddress != null ? cardHolderAddress.hashCode() : 0);
        result = 31 * result + (encryptedCardNumber != null ? encryptedCardNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "CardDetails{" +
                "cardNumber='" + CardObfuscator.mask(cardNumber) + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cvc=" + cvc +
                ", cardHolderAddress=" + cardHolderAddress +
                ", encryptedCardNumber='" + encryptedCardNumber + '\'' +
                '}';
    }

}
