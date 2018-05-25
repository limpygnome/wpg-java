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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalReference reference1 = (JournalReference) o;

        if (type != null ? !type.equals(reference1.type) : reference1.type != null) return false;
        return reference != null ? reference.equals(reference1.reference) : reference1.reference == null;
    }

    @Override
    public int hashCode()
    {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        return result;
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
