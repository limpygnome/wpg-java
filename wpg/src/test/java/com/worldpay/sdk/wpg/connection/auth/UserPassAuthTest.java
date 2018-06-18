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

    @Test
    public void constructor_blankInstallationId()
    {
        UserPassAuth auth = new UserPassAuth("user", "pass", "merchant code", "");
        assertNull(auth.getInstallationId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_invalidFormat()
    {
        new UserPassAuth("user", "pass", "merchant code", "not numeric");
    }

    @Test
    public void constructor_asExpected()
    {
        UserPassAuth auth = new UserPassAuth("user", "pass", "merchant code", "12345");
        assertEquals("user", auth.getUser());
        assertEquals("pass", auth.getPass());
        assertEquals("merchant code", auth.getMerchantCode());
        assertEquals("12345", auth.getInstallationId());
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