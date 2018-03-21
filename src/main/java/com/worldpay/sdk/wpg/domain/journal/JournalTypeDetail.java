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
    public String toString()
    {
        return "JournalTypeDetail{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", reversalReasons=" + reversalReasons +
                '}';
    }
}
