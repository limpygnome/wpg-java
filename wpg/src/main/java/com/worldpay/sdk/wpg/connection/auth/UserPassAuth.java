package com.worldpay.sdk.wpg.connection.auth;

/**
 * Basic user/pass authentication for a merchant code and installation.
 *
 * An installation (id) is optional, but this depends on your integration. Ask your integration manager, or corporate
 * support, for further help.
 */
public class UserPassAuth implements Auth
{
    private String user;
    private String pass;
    private String merchantCode;
    private String installationId;

    public UserPassAuth(String user, String pass, String merchantCode)
    {
        this(user, pass, merchantCode, null);
    }

    public UserPassAuth(String user, String pass, String merchantCode, String installationId)
    {
        // Installation is optional
        if (installationId != null && installationId.isEmpty())
        {
            installationId = null;
        }

        // Validate
        if (user == null || user.length() == 0)
        {
            throw new IllegalArgumentException("User is mandatory");
        }
        else if (pass == null || pass.length() == 0)
        {
            throw new IllegalArgumentException("Password is mandatory");
        }
        else if (merchantCode == null || merchantCode.length() == 0)
        {
            throw new IllegalArgumentException("Merchant code is mandatory");
        }
        else if (installationId != null && !installationId.matches("^[0-9]+$"))
        {
            throw new IllegalArgumentException("Installation (id) expected to be a numeric ID / value");
        }

        // Setup
        this.user = user;
        this.pass = pass;
        this.merchantCode = merchantCode;
        this.installationId = installationId;
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

    public String getInstallationId()
    {
        return installationId;
    }

}
