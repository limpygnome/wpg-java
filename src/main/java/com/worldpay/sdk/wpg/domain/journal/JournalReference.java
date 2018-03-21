package com.worldpay.sdk.wpg.domain.journal;

public class JournalReference
{
    private final String type;
    private final String reference;

    public JournalReference(String type, String reference)
    {
        this.type = type;
        this.reference = reference;
    }

    public String getType()
    {
        return type;
    }

    public String getReference()
    {
        return reference;
    }

    @Override
    public String toString()
    {
        return "JournalReference{" +
                "type='" + type + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
