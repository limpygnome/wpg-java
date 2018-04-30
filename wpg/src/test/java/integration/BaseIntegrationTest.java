package integration;

public class BaseIntegrationTest
{
    protected static final String USER;
    protected static final String PASS;
    protected static final String MERCHANT_CODE;
    protected static final String INSTALLATION_ID;

    static
    {
        USER = System.getProperty("sdk.user");
        PASS = System.getProperty("sdk.pass");
        MERCHANT_CODE = System.getProperty("sdk.merchantCode");
        INSTALLATION_ID = System.getProperty("sdk.installationId");

        if (USER == null || PASS == null || MERCHANT_CODE == null || INSTALLATION_ID == null)
        {
            throw new IllegalStateException("Tests ran without credentials specified");
        }
    }

}
