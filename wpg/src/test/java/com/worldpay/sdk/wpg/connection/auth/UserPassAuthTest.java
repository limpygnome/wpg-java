package com.worldpay.sdk.wpg.connection.auth;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserPassAuthTest
{

    @Test(expected = IllegalArgumentException.class)
    public void constructor_missingUser()
    {
        new UserPassAuth(null, "pass", "merchant code", "installation id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_missingPass()
    {
        new UserPassAuth("user", null, "merchant code", "installation id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_missingMerchantCode()
    {
        new UserPassAuth("user", "pass", null, "installation id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_blankInstallationId()
    {
        new UserPassAuth("user", "pass", "merchant code", "");
    }

    @Test
    public void constructor_asExpected()
    {
        UserPassAuth auth = new UserPassAuth("user", "pass", "merchant code", "installation id");
        assertEquals("user", auth.getUser());
        assertEquals("pass", auth.getPass());
        assertEquals("merchant code", auth.getMerchantCode());
        assertEquals("installation id", auth.getInstallationId());
    }

    @Test
    public void constructor_nullInstallationId()
    {
        UserPassAuth auth = new UserPassAuth("user", "pass", "merchant code");
        assertEquals("user", auth.getUser());
        assertEquals("pass", auth.getPass());
        assertEquals("merchant code", auth.getMerchantCode());
        assertNull(auth.getInstallationId());
    }

}