package com.worldpay.sdk.wpg.domain.journal;

import java.util.List;

public class JournalTypeDetail
{
    private final String id;
    private final String description;
    private final List<String> reversalReasons;

    public JournalTypeDetail(String id, String description, List<String> reversalReasons)
    {
        this.id = id;
        this.description = description;
        this.reversalReasons = reversalReasons;
    }

    public String getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public List<String> getReversalReasons()
    {
        return reversalReasons;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalTypeDetail that = (JournalTypeDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return reversalReasons != null ? reversalReasons.equals(that.reversalReasons) : that.reversalReasons == null;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reversalReasons != null ? reversalReasons.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "JournalTypeDetail{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", reversalReasons=" + reversalReasons +
                '}';
    }
}
