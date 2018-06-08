package com.worldpay.sdk.demoshop.domain;

public class ApiTokenDetails
{
    private String tokenId;
    private boolean captureCvc;

    public ApiTokenDetails() { }

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }

    public boolean isCaptureCvc()
    {
        return captureCvc;
    }

    public void setCaptureCvc(boolean captureCvc)
    {
        this.captureCvc = captureCvc;
    }

}
