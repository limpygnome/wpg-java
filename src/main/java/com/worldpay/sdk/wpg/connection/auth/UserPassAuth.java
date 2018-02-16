package com.worldpay.sdk.wpg.connection.auth;

public class UserPassAuth implements Auth
{
    private String user;
    private String pass;
    private String merchantCode;
    private Long installationId;

    public UserPassAuth(String user, String pass, String merchantCode, Long installationId)
    {
        this.user = user;
        this.pass = pass;
        this.merchantCode = merchantCode;
        this.installationId = installationId;

        // TODO validation
    }

    public String getUser()
    {
        return user;
    }

    public String getPass()
    {
        return pass;
    }

    public String getMerchantCode()
    {
        return merchantCode;
    }

    public Long getInstallationId()
    {
        return installationId;
    }

}
